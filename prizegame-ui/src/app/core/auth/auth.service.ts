import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {HttpClient} from '@angular/common/http';
import {JwtHelperService} from '@auth0/angular-jwt';
import {TOKEN_KEY} from './token.storage';

@Injectable()
export class AuthService {

  constructor(private http: HttpClient) {
  }

  attemptAuth(username: string, password: string): Observable<any> {
    const credentials = {username: username, password: password};
    return this.http.post<any>('token/generate-token', credentials);
  }

  public isAuthenticated(): boolean {
    const jwtHelper = new JwtHelperService();
    const token = sessionStorage.getItem(TOKEN_KEY);
    // Check whether the token is expired and return
    // true or false
    return !jwtHelper.isTokenExpired(token);
  }

}
