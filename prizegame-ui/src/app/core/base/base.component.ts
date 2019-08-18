import {Component, OnInit} from '@angular/core';
import {User} from '../../restricted/user/user.model';
import {UserStorage} from '../auth/user.storage';

@Component({
  selector: 'app-base',
  templateUrl: './base.component.html',
  styleUrls: ['./base.component.css']
})
export class BaseComponent implements OnInit {

  currentUser: User;

  constructor(protected tokenStorage: UserStorage) {
    this.fetchCurrentUser();
  }

  ngOnInit() {
    this.fetchCurrentUser();
  }

  public fetchCurrentUser() {
    this.currentUser = this.tokenStorage.getCurrentUser();
  }

}
