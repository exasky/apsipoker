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

  create(championship: Championship): Observable<Championship> {
    return this.http.post<Championship>(ChampionshipService.API_URL, championship);
  }
}
