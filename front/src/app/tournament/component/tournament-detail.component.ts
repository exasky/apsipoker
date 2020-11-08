import {Component, HostBinding, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {TournamentService} from '../service/tournament.service';
import {TournamentDetail} from '../model/tournament-detail';
import {AuthService} from '../../login/auth.service';
import {CdkDragDrop, moveItemInArray} from '@angular/cdk/drag-drop';
import {TournamentPlayer} from '../../championship/model/championship';
import {ToasterService} from '../../common/service/toaster.service';

@Component({
  selector: 'app-tournament-detail',
  templateUrl: './tournament-detail.component.html',
  styleUrls: ['./tournament-detail.component.scss']
})
export class TournamentDetailComponent implements OnInit {
  @HostBinding('class') cssClass = 'flex-grow d-flex flex-column m-5';

  baseTournament: TournamentDetail = new TournamentDetail();
  tournament: TournamentDetail = new TournamentDetail();

  isEdit = false;

  displayedColumns = ['position', 'points', 'username', 'firstname', 'lastname'];

  constructor(private route: ActivatedRoute,
              private tournamentService: TournamentService,
              public authService: AuthService,
              private toaster: ToasterService) {
  }

  ngOnInit(): void {
    this.tournamentService.getById(this.route.snapshot.paramMap.get('id')).subscribe(tournament => {
      this.setTournament(tournament);
    });
  }

  setTournament(tournament: TournamentDetail): void {
    this.baseTournament = tournament;
    this.baseTournament.participants.sort((p1, p2) => p1.position - p2.position);

    this.tournament = JSON.parse(JSON.stringify(this.baseTournament));

    this.isEdit = false;
  }

  edit(): void {
    this.isEdit = true;
  }

  cancel(): void {
    this.setTournament(this.baseTournament);
  }

  save(): void {
    this.baseTournament = this.tournament;
    this.tournamentService.update(this.baseTournament).subscribe(savedTournament => {
      this.setTournament(savedTournament);
      this.toaster.success('Tounoi mis à jour !');
    });
  }

  drop(event: CdkDragDrop<TournamentPlayer[], any>): void {
    const previousIndex = this.tournament.participants.findIndex(p => p.id === event.item.data.id);
    moveItemInArray(this.tournament.participants, previousIndex, event.currentIndex);

    this.tournament.participants.forEach((p, i) => p.position = i + 1);

    this.tournament.participants = [...this.tournament.participants];
  }
}
