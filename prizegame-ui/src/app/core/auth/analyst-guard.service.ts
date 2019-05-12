import {Injectable} from '@angular/core';
import {CanActivate, Router} from '@angular/router';
import {TokenStorage} from './token.storage';
import {User} from '../../restricted/user/user.model';

@Injectable()
export class AnalystGuardService implements CanActivate {

  currentUser: User;

  constructor(public router: Router, private tokenStorage: TokenStorage) {

  }

  canActivate(): boolean {
    this.currentUser = this.tokenStorage.getCurrentUser();
    let isAnalyst: boolean = this.currentUser.isAnalyst();
    if (!isAnalyst) {
      this.router.navigate(['unauthorized']);
    }
    return isAnalyst;
  }

}
