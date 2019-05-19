import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {UserComponent} from "./restricted/user/user.component";
import {CustomMaterialModule} from "./core/material.module";
import {HomeComponent} from "./restricted/home/home.component";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {SnackBarService} from "./core/shared-components/snackbar/snackbar.service";
import {ErrorDialogComponent} from "./core/shared-components/error-dialog.component";
import {ConfirmationDialogComponent} from "./restricted/dialog/confirmation-dialog.component";
import {UserDetailsComponent} from "./restricted/user/user-details/user-details.component";
import {AuthGuardService} from "./core/auth/auth-guard.service";
import {TokenStorage} from "./core/auth/token.storage";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {BaseComponent} from "./core/base/base.component";
import {Ng4LoadingSpinnerModule} from "ng4-loading-spinner";
import {MAT_SNACK_BAR_DEFAULT_OPTIONS} from "@angular/material";
import {AdminGuardService} from "./core/auth/admin-guard.service";
import {LoginComponent} from "./public/login/login.component";
import {UnauthorizedComponent} from "./public/unauthorized/unauthorized.component";
import {AnalystGuardService} from "./core/auth/analyst-guard.service";
import {SpinnerComponent} from "./core/shared-components/spinner/spinner.component";
import {AuthService} from "./core/auth/auth.service";
import {AlertService} from "./core/alert/alert-service";
import {Interceptor} from "./core/inteceptor";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {UserService} from "./restricted/user/user.service";
import {AppHeaderComponent} from "./header/app-header.component";
import {AutocompleteComponent} from "./core/shared-components/autocomplete/autocomplete.component";
import {AppHeaderMessengerService} from "./header/app-header-messager.service";
import {QuickGuideComponent} from "./restricted/quick-guide/quick-guide.component";
import {CodeDetailsComponent} from "./restricted/code/code-details/code-details.component";
import {CodeComponent} from "./restricted/code/code.component";
import {CodeService} from "./restricted/code/code.service";

@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
    UserDetailsComponent,
    CodeComponent,
    CodeDetailsComponent,
    AppHeaderComponent,
    LoginComponent,
    ErrorDialogComponent,
    ConfirmationDialogComponent,
    SpinnerComponent,
    AutocompleteComponent,
    HomeComponent,
    QuickGuideComponent,
    UnauthorizedComponent,
    BaseComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    BrowserAnimationsModule,
    CustomMaterialModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    Ng4LoadingSpinnerModule.forRoot()
  ],
  entryComponents: [ErrorDialogComponent, ConfirmationDialogComponent, SpinnerComponent, AutocompleteComponent, UnauthorizedComponent],
  providers: [ErrorDialogComponent, UserService, CodeService, AuthService, TokenStorage, TokenStorage, AuthGuardService, AdminGuardService, AnalystGuardService, AppHeaderMessengerService, AlertService, SnackBarService,

    {
      provide: HTTP_INTERCEPTORS,
      useClass: Interceptor,
      multi: true
    },
    {
      provide: MAT_SNACK_BAR_DEFAULT_OPTIONS,
      useValue: {duration: 5000}
    }],
  bootstrap: [AppComponent]
})
export class AppModule {

}
