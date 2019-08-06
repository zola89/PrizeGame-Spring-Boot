import {Injectable} from '@angular/core';
import {JwtHelperService} from '@auth0/angular-jwt';
import {User} from '../../restricted/user/user.model';


export const TOKEN_KEY = 'AuthToken';

@Injectable()
export class TokenStorage {

  constructor() {

  }

  signOut() {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.clear();
  }

  public saveToken(token: string) {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);
  }

  public getToken(): string {
    return sessionStorage.getItem(TOKEN_KEY);
  }

  public getDecodedToken(): any {
    let jwtHelper = new JwtHelperService();
    return jwtHelper.decodeToken(this.getToken());
  }

  public getUsername(): string {
    let decodeToken = this.getDecodedToken();
    return decodeToken ? decodeToken.sub : null;
  }

  public getUserId(): string {
    let decodeToken = this.getDecodedToken();
    return decodeToken ? decodeToken.id : null;
  }

  public getCurrentUser(): User {

    const token = this.getDecodedToken();

    if (token === null || token === undefined) {
      return null;
    }

    let user = new User();
    user.name = token.sub;
    user.userRole = token.scopes[0].authority;

    return user;
  }


}
