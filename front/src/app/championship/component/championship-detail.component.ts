import {Component, HostBinding, OnInit} from '@angular/core';
import {ChampionshipService} from '../service/championship.service';
import {ActivatedRoute} from '@angular/router';
import {Championship} from '../model/championship';

@Component({
  selector: 'app-championship-detail',
  templateUrl: './championship-detail.component.html'
})
export class ChampionshipDetailComponent implements OnInit {
  @HostBinding('class') cssClass = 'flex-grow d-flex flex-column m-5';

  championship: Championship;
  userPoints: Map<number, number> = new Map();

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

    this.championship.tournaments.forEach(t => {
      console.log(t);
      t.participants.forEach(p => {
        this.userPoints.set(p.player.id, (this.userPoints.get(p.player.id) || 0) + p.points);
      });
    });

    console.log(this.userPoints);
  }
}
