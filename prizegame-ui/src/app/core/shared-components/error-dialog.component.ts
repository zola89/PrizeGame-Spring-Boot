import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';

@Component({
  templateUrl: 'error-dialog.component.html'
})

export class ErrorDialogComponent {

  constructor(private dialogRef: MatDialogRef<ErrorDialogComponent>, @Inject(MAT_DIALOG_DATA) public data: any) {

  }

  public closeDialog() {
    this.dialogRef.close();
  }

}
