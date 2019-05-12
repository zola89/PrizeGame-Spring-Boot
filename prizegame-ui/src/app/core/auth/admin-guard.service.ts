import {Injectable} from '@angular/core';
import {CanActivate, Router} from '@angular/router';
import {TokenStorage} from './token.storage';
import {User} from '../../restricted/user/user.model';

@Injectable()
export class AdminGuardService implements CanActivate {

  constructor(public router: Router, private tokenStorage: TokenStorage) {

  }

  canActivate(): boolean {
    let currentUser: User = this.tokenStorage.getCurrentUser();
    let isAdmin: boolean = currentUser.isAdmin();
    if (!isAdmin) {
      this.router.navigate(['unauthorized']);
    }
    return isAdmin;
  }

}
