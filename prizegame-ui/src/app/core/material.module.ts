import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {
  MatAutocompleteModule,
  MatButtonModule,
  MatCardModule,
  MatCheckboxModule,
  MatChipsModule,
  MatDialogModule,
  MatExpansionModule,
  MatGridListModule,
  MatIconModule,
  MatInputModule,
  MatListModule,
  MatMenuModule,
  MatOptionModule,
  MatPaginatorModule,
  MatProgressSpinnerModule,
  MatSelectModule,
  MatTableModule,
  MatTabsModule,
  MatToolbarModule,
  MatDatepickerModule,
  MatNativeDateModule,
  MatSnackBarModule
} from '@angular/material';

@NgModule({
  imports: [CommonModule, MatToolbarModule, MatButtonModule, MatCardModule, MatInputModule, MatDialogModule, MatTableModule, MatMenuModule, MatIconModule, MatGridListModule, MatSelectModule, MatOptionModule, MatCheckboxModule, MatExpansionModule, MatAutocompleteModule, MatChipsModule, MatListModule, MatTabsModule, MatProgressSpinnerModule, MatPaginatorModule, MatDatepickerModule ,MatNativeDateModule, MatSnackBarModule],
  exports: [CommonModule, MatToolbarModule, MatButtonModule, MatCardModule, MatInputModule, MatDialogModule, MatTableModule, MatMenuModule, MatIconModule, MatGridListModule, MatSelectModule, MatOptionModule, MatCheckboxModule, MatExpansionModule, MatAutocompleteModule, MatChipsModule, MatListModule, MatTabsModule, MatProgressSpinnerModule, MatPaginatorModule, MatDatepickerModule ,MatNativeDateModule, MatSnackBarModule],
  declarations: []
})
export class CustomMaterialModule {

}
