import {Injectable} from '@angular/core';
import {CanActivate, Router} from '@angular/router';
import {UserStorage} from './user.storage';
import {User} from '../../restricted/user/user.model';

@Injectable()
export class AdminGuardService implements CanActivate {

  constructor(public router: Router, private userStorage: UserStorage) {

  }

  canActivate(): boolean {
    const currentUser = this.userStorage.getCurrentUser();
    const isAdmin: boolean = currentUser.isAdmin();
    if (!isAdmin) {
      this.router.navigate(['unauthorized']);
    }
    return isAdmin;
  }

}
