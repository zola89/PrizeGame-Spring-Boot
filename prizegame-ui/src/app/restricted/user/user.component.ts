import {Component, HostListener, OnInit, ViewChild} from '@angular/core';
import {MatDialog, MatDialogRef, MatPaginator, MatTableDataSource} from '@angular/material';
import {User} from './user.model';
import {Router} from '@angular/router';
import {UserService} from './user.service';
import {ConfirmationDialogComponent} from '../dialog/confirmation-dialog.component';
import {TokenStorage} from '../../core/auth/token.storage';
import {BaseComponent} from '../../core/base/base.component';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent extends BaseComponent implements OnInit {

  dataSource = new MatTableDataSource<User>();
  dialogRef: MatDialogRef<ConfirmationDialogComponent>;
  screenHeight: number;

  @HostListener('window:resize', ['$event'])
  onResize() {
    this.screenHeight = window.innerHeight;
  }

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private router: Router, private userService: UserService, public dialog: MatDialog, tokenStorage: TokenStorage) {
    super(tokenStorage);
    this.screenHeight = window.innerHeight;
  }

  ngOnInit(): void {
    this.dataSource.paginator = this.paginator;
    this.userService.getUsers().subscribe(
      data => {
        this.dataSource.data = data;
      }
    );
  }

  addNew() {
    this.router.navigateByUrl('user/details');
  }

  edit(id: any) {
    this.router.navigateByUrl('user/details/' + id);
  }

  delete(index: number, id: any) {
    this.userService.deleteById(id).subscribe(
      data => {
        this.userService.getUsers().subscribe(
          data => {
            this.dataSource.data = data;
            this.router.navigateByUrl('configure_system/user');
          }
        );
      }
    )
  }

  openConfirmationDialog(index: number, id: any) {
    this.dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      disableClose: false
    });

    this.dialogRef.componentInstance.dialogTitle = 'Confirm Delete User';
    this.dialogRef.componentInstance.data = 'Are you sure you want to delete user?';
    this.dialogRef.componentInstance.dialogButtonYes = 'Delete';
    this.dialogRef.componentInstance.dialogButtonNo = 'Cancel';

    this.dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.delete(index, id);
      }
      this.dialogRef = null;
    });
  }

}


