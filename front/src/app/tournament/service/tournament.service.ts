import {Injectable} from '@angular/core';
import {environment} from '../../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {TournamentDetail} from '../model/tournament-detail';

@Injectable({
  providedIn: 'root'
})
export class TournamentService {
  private static API_URL = environment.apiUrl + '/tournament';

  constructor(private http: HttpClient) {
  }

  getById(id: string | number): Observable<TournamentDetail> {
    return this.http.get<TournamentDetail>(TournamentService.API_URL + '/' + id);
  }

  update(tournament: TournamentDetail): Observable<TournamentDetail> {
    return this.http.put<TournamentDetail>(TournamentService.API_URL + '/' + tournament.id, tournament);
  }
}
