import {Component, OnDestroy, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {UserStorage} from '../core/auth/user.storage';
import {Subscription} from 'rxjs/Subscription';
import {AppHeaderMessengerService} from './app-header-messager.service';
import {ConfirmationDialogComponent} from '../restricted/dialog/confirmation-dialog.component';
import {MatDialog, MatDialogRef} from '@angular/material';
import {BaseComponent} from '../core/base/base.component';
import {isNullOrUndefined} from 'util';
import { User } from '../restricted/user/user.model';

@Component({
  selector: 'app-header',
  templateUrl: './app-header.component.html'
})
export class AppHeaderComponent implements OnInit, OnDestroy {

  dialogRef: MatDialogRef<ConfirmationDialogComponent>;

  currentUser: User;

  private messageSubscription: Subscription;

  constructor(private router: Router, public userStorage: UserStorage, private appHeaderMessengerService: AppHeaderMessengerService, public dialog: MatDialog) {
  }

  ngOnInit() {
    this.messageSubscription = this.appHeaderMessengerService.message.subscribe(m => {
      this.refreshCurrentUser();
    });
  }

  ngOnDestroy() {
    this.messageSubscription.unsubscribe();
  }

  logOut() {
    this.currentUser = null;
    this.userStorage.signOut();
    this.router.navigateByUrl('/');
  }

  codes() {
    if (!isNullOrUndefined(this.currentUser) && this.currentUser.user_role === 'ADMIN') {
      this.router.navigateByUrl('code');
    } else {
      this.router.navigateByUrl('code/user/' + this.currentUser.id);
    }
  }
  
  users() {
    if (!isNullOrUndefined(this.currentUser) && this.currentUser.user_role === 'ADMIN') {
      this.router.navigateByUrl('user');
    } else {
      this.router.navigateByUrl('user/details/' + this.userStorage.getCurrentUserId());
    }

  }

  editProfile() {
    this.router.navigateByUrl('user/details/' + this.userStorage.getCurrentUserId());
  }

  refreshCurrentUser() {
    this.currentUser = this.userStorage.getCurrentUser();
  }

  quickGuide() {
    this.router.navigateByUrl('quick_guide');
  }

  openConfirmationDialog() {
    this.dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      disableClose: false
    });

    this.dialogRef.componentInstance.dialogTitle = 'Confirm Logout';
    this.dialogRef.componentInstance.data = 'Are you sure you want to log out?';
    this.dialogRef.componentInstance.dialogButtonYes = 'Yes';
    this.dialogRef.componentInstance.dialogButtonNo = 'Cancel';

    this.dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.logOut();
      }
      this.dialogRef = null;
    });
  }

  openHomePage() {
    this.router.navigateByUrl('home');
  }

}
