import {Component, HostListener, OnInit, ViewChild} from '@angular/core';
import {MatDialog, MatDialogRef, MatPaginator, MatTableDataSource} from "@angular/material";
import {ConfirmationDialogComponent} from "../dialog/confirmation-dialog.component";
import {Router} from "@angular/router";
import {Code} from "./code.model";
import {CodeService} from "./code.service";

@Component({
  selector: 'app-code',
  templateUrl: './code.component.html',
  styleUrls: ['./code.component.sass']
})
export class CodeComponent implements OnInit {

  dataSource = new MatTableDataSource<Code>();
  dialogRef: MatDialogRef<ConfirmationDialogComponent>;
  screenHeight: number;

  @HostListener('window:resize', ['$event'])
  onResize() {
    this.screenHeight = window.innerHeight;
  }

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private router: Router, private codeService: CodeService, public dialog: MatDialog) {
    this.screenHeight = window.innerHeight;
  }

  ngOnInit(): void {
    this.dataSource.paginator = this.paginator;
    this.codeService.getCodes().subscribe(
        data => {
          this.dataSource.data = data;
        }
    );
  }

  addNew() {
    this.router.navigateByUrl('code/details');
  }

  edit(id: any) {
    this.router.navigateByUrl('code/details/' + id);
  }

  delete(index: number, id: any) {
    this.codeService.deleteById(id).subscribe(
        data => {
          this.codeService.getCodes().subscribe(
              data => {
                this.dataSource.data = data;
                this.router.navigateByUrl('code');
              }
          );
        }
    )
  }

  openConfirmationDialog(index: number, id: any) {
    this.dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      disableClose: false
    });

    this.dialogRef.componentInstance.dialogTitle = 'Confirm Delete Code';
    this.dialogRef.componentInstance.data = 'Are you sure you want to delete code?';
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
