import {Component, HostBinding, OnInit} from '@angular/core';
import {ToasterService} from '../../common/service/toaster.service';
import {MatDialog} from '@angular/material/dialog';
import {ChampionshipService} from '../service/championship.service';
import {Championship, Player, Tournament, TournamentPlayer} from '../model/championship';
import {UserEdit} from '../../user/model/user-edit';
import {UserService} from '../../user/service/user.service';
import {ConfirmDialogComponent} from '../../common/dialog/confirm-dialog.component';
import {FormControl} from '@angular/forms';

@Component({
  selector: 'app-championship-list',
  templateUrl: './championship-list.component.html',
  styleUrls: ['./championship-list.component.scss']
})
export class ChampionshipListComponent implements OnInit {
  @HostBinding('class') cssClass = 'flex-grow d-flex';

  championships: Championship[] = [];
  selectedChampionship: Championship;

  isEdit = false;
  championshipEdit: Championship = new Championship();

  users: UserEdit[] = [];

  selected = new FormControl(0);

  constructor(private championshipService: ChampionshipService,
              private userService: UserService,
              private toaster: ToasterService,
              private dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.championshipService.getAll().subscribe(championships => {
      this.championships = championships;
    });
    this.userService.getAll().subscribe(users => {
      this.users = users;
    });
  }

  create(): void {
    this.select(new Championship());
    this.isEdit = true;
  }

  select(championship: Championship): void {
    this.selectedChampionship = championship;

    if (!championship) {
      return;
    }

    this.championshipEdit = JSON.parse(JSON.stringify(this.selectedChampionship));

    this.isEdit = false;
  }

  edit(): void {
    this.isEdit = true;
  }

  cancel(): void {
    this.select(this.selectedChampionship.id ? this.selectedChampionship : null);
  }

  save(): void {
    this.selectedChampionship = this.championshipEdit;
    if (this.selectedChampionship.id !== undefined) {
      this.championshipService.update(this.selectedChampionship).subscribe(savedChampionship => {
        this.select(savedChampionship);
        this.championships[this.championships.findIndex(champ => champ.id === savedChampionship.id)] = savedChampionship;
        this.toaster.success('Championnat ' + savedChampionship.name + ' mis à jour !');
      });
    } else {
      this.championshipService.create(this.selectedChampionship).subscribe(createdChampionship => {
        this.select(createdChampionship);
        this.championships.push(createdChampionship);
        this.toaster.success('Championnat ' + createdChampionship.name + ' crée !');
      });
    }
  }

  updateChampionshipPlayers($event: UserEdit[]): void {
    this.championshipEdit.participants = $event as Player[];
    this.championshipEdit.tournaments.forEach(tournament => {
      tournament.participants = tournament.participants.filter(tournamentParticipant =>
        this.championshipEdit.participants
          .some(championshipParticipant => championshipParticipant.id === tournamentParticipant.player.id)
      );
    });
  }

  delete(): void {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      width: '250px',
      data: {
        title: 'Suppression de championnat',
        confirmMessage: 'Êtes-vous sur de vouloir supprimer le championnat ' + this.selectedChampionship.name + '?'
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.championshipService.delete(this.selectedChampionship.id).subscribe(() => {
          this.ngOnInit();
          this.toaster.success('Championnat ' + this.selectedChampionship.name + ' supprimé !');
          this.select(null);
        });
      }
    });
  }

  addTournament(event: MouseEvent): void {
    event.preventDefault();
    event.stopImmediatePropagation();
    this.championshipEdit.tournaments.push(new Tournament());
    this.selected.setValue(this.championshipEdit.tournaments.length + 1);
  }

  updateTournamentPlayers(tournament: Tournament, $event: UserEdit[]): void {
    tournament.participants = $event.map(user => {
      let tp = tournament.participants.find(tPlayer => tPlayer.player.id === user.id);
      if (!tp) {
        tp = new TournamentPlayer();
        tp.player = user as Player;
      }
      return tp;
    });
  }
}
