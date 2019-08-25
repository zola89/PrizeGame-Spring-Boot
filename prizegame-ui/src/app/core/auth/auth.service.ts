import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {HttpClient} from '@angular/common/http';
import {JwtHelperService} from '@auth0/angular-jwt';
import {currentUser, UserStorage} from './user.storage';
import {isNullOrUndefined} from 'util';
import {parseSelectorToR3Selector} from '@angular/compiler/src/core';
import {User} from '../../restricted/user/user.model';
import {userError} from '@angular/compiler-cli/src/transformers/util';

@Injectable()
export class AuthService {

  constructor(private http: HttpClient, private userStorage: UserStorage) {
  }

  attemptAuth(email: string, password: string): Observable<any> {
    const user = new User();
    user.email = email;
    user.password = password;
    return this.http.post<any>('users/validate', user);
  }

  public isAuthenticated(): boolean {
    const user = this.userStorage.getCurrentUser();
    return  !isNullOrUndefined(user);
  }

}
