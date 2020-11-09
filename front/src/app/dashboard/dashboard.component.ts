import {Component, HostBinding, OnInit} from '@angular/core';
import {DashboardService} from './service/dashboard.service';
import {Championship} from '../championship/model/championship';
import {TournamentUtils} from '../common/utils/tournament.utils';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  @HostBinding('class') cssClass = 'flex-grow d-flex flex-column align-items-center justify-content-evenly';

  championships: Championship[];

  constructor(public dashboardService: DashboardService) {
  }

  ngOnInit(): void {
    this.dashboardService.getDashboard().subscribe(championships => {
      this.championships = championships;
      this.championships.forEach(c => {
        c.tournaments.sort(TournamentUtils.sortTournament);
      });
    });
  }
}
