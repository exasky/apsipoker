import {Injectable} from '@angular/core';
import {environment} from '../../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Championship} from '../../championship/model/championship';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {
  private static API_URL = environment.apiUrl + '/dashboard';

  constructor(private http: HttpClient) {
  }

  getDashboard(): Observable<Championship[]> {
    return this.http.get<Championship[]>(DashboardService.API_URL);
  }
}
