import {Component, HostBinding, OnInit} from '@angular/core';
import {ChampionshipService} from '../service/championship.service';
import {ActivatedRoute} from '@angular/router';
import {Championship} from '../model/championship';
import {TournamentUtils} from '../../common/utils/tournament.utils';

@Component({
  selector: 'app-championship-detail',
  templateUrl: './championship-detail.component.html'
})
export class ChampionshipDetailComponent implements OnInit {
  @HostBinding('class') cssClass = 'flex-grow d-flex flex-column m-5';

  championship: Championship = new Championship();
  userPoints: Map<number, number> = new Map();

  displayedColumns = ['position', 'username', 'firstname', 'total', 'positions'];

  constructor(private route: ActivatedRoute,
              private championshipService: ChampionshipService) {
  }

  ngOnInit(): void {
    this.championshipService.getById(this.route.snapshot.paramMap.get('id')).subscribe(championship => {
      this.setChampionship(championship);
    });
  }

  private setChampionship(championship: Championship): void {
    this.championship = championship;

    this.championship.tournaments.sort(TournamentUtils.sortTournament);

    // TODO display tournament positions
    // for (let i = 0; i < this.championship.tournaments.length; i++) {
    //   this.displayedColumns.push('tournament-' + i);
    // }

    this.championship.tournaments.forEach(t => {
      console.log(t);
      t.participants.forEach(p => {
        this.userPoints.set(p.player.id, (this.userPoints.get(p.player.id) || 0) + p.points);
      });
    });

    this.championship.participants.sort((p1, p2) => this.userPoints.get(p2.id) - this.userPoints.get(p1.id));

    console.log(this.userPoints);
  }

  getPositions(playerId): string[] {
    return this.championship.tournaments.map(t => {
      const participation = t.participants.find(p => p.player.id === playerId);
      return participation ? participation.position as unknown as string : 'NP';
    });
  }
}
