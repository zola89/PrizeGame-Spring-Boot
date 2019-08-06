import {Component, OnDestroy, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {TokenStorage} from '../core/auth/token.storage';
import {Subscription} from 'rxjs/Subscription';
import {AppHeaderMessengerService} from './app-header-messager.service';
import {ConfirmationDialogComponent} from '../restricted/dialog/confirmation-dialog.component';
import {MatDialog, MatDialogRef} from '@angular/material';
import {BaseComponent} from '../core/base/base.component';

@Component({
  selector: 'app-header',
  templateUrl: './app-header.component.html'
})
export class AppHeaderComponent extends BaseComponent implements OnInit, OnDestroy {

  dialogRef: MatDialogRef<ConfirmationDialogComponent>;


  private messageSubscription: Subscription;

  constructor(private router: Router, public token: TokenStorage, private appHeaderMessengerService: AppHeaderMessengerService, public dialog: MatDialog) {
    super(token);
    this.refreshCurrentUser();
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
    this.token.signOut();
    this.router.navigateByUrl('/');
  }

  codes() {
    this.router.navigateByUrl('code');
  }
  
  users() {
    this.router.navigateByUrl('user');
  }

  editProfile() {
    this.router.navigateByUrl('user/details/' + this.token.getUserId());
  }

  refreshCurrentUser() {
    this.currentUser = this.token.getCurrentUser();
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
