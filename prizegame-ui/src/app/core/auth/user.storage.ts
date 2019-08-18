import {Injectable} from '@angular/core';
import {User} from '../../restricted/user/user.model';
import {isNullOrUndefined} from 'util';


export const currentUser = 'currentUser';

@Injectable()
export class UserStorage {

  constructor() {

  }

  signOut() {
    window.sessionStorage.removeItem(currentUser);
    window.sessionStorage.clear();
  }

  public saveCurrentUser(user: string) {
    window.sessionStorage.removeItem(currentUser);
    window.sessionStorage.setItem(currentUser, user);
  }

  public getCurrentUser(): User {

    if (isNullOrUndefined(sessionStorage.getItem(currentUser))) {
      return null;
    }

    const user = JSON.parse(sessionStorage.getItem(currentUser));
    return user;
  }

  public getUsername(): string {
    const user = this.getCurrentUser();
    return user ? user.name : null;
  }

  public getCurrentUserId(): number {
    const user = this.getCurrentUser();
    return user ? user.id : null;
  }

}
