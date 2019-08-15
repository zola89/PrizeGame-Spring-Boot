import {Component, HostListener, OnInit, ViewChild} from '@angular/core';
import {MatDialog, MatDialogRef, MatPaginator, MatTable, MatTableDataSource} from "@angular/material";
import {ConfirmationDialogComponent} from "../dialog/confirmation-dialog.component";
import {ActivatedRoute, Router} from "@angular/router";
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
  id: number;

  @ViewChild('codeTable')
  codeTable: MatTable<Code>;

  @HostListener('window:resize', ['$event'])
  onResize() {
    this.screenHeight = window.innerHeight;
  }

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private router: Router, protected route: ActivatedRoute, private codeService: CodeService, public dialog: MatDialog) {
    this.screenHeight = window.innerHeight;
  }

  ngOnInit(): void {
    this.dataSource.paginator = this.paginator;
    
    this.route.params.subscribe(params => {
      this.id = +params['id'];
    });


    if (this.id) {
      this.codeService.getByUserId(this.id).subscribe(
          data => {
            console.log(data);
            this.dataSource.data = data;
          }
      );
    } else {
    	this.codeService.getCodes().subscribe(
        	data => {
          		this.dataSource.data = data;
        	}
    	);
    
    }
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
