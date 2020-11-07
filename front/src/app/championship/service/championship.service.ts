import {Injectable} from '@angular/core';
import {environment} from '../../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Championship} from '../model/championship';

@Injectable({
  providedIn: 'root'
})
export class ChampionshipService {
  private static API_URL = environment.apiUrl + '/championship';


  constructor(private http: HttpClient) {
  }

  getAll(): Observable<Championship[]> {
    return this.http.get<Championship[]>(ChampionshipService.API_URL);
  }

  create(championship: Championship): Observable<Championship> {
    return this.http.post<Championship>(ChampionshipService.API_URL, championship);
  }

  update(championship: Championship): Observable<Championship> {
    return this.http.put<Championship>(ChampionshipService.API_URL + '/' + championship.id, championship);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(ChampionshipService.API_URL + '/' + id);
  }
}
