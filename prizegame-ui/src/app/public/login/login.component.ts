import {Component, HostListener} from '@angular/core';
import {Router} from '@angular/router';
import {AuthService} from '../../core/auth/auth.service';
import {TokenStorage} from '../../core/auth/token.storage';
import {AppHeaderMessengerService} from '../../header/app-header-messager.service';
import {AlertService} from '../../core/alert/alert-service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  username: string;
  password: string;


  constructor(private router: Router,
              private authService: AuthService,
              private token: TokenStorage,
              private appHeaderMessengerService: AppHeaderMessengerService,
              private alertService: AlertService) {
  }

  login(): void {

    this.token.signOut();

    this.authService.attemptAuth(this.username, this.password).subscribe(
      data => {
        this.token.saveToken(data.token);
        this.appHeaderMessengerService.refresh(true);
        this.router.navigate(['home']);
      },
      err => {
        if (err.status == 500) {
          this.alertService.hideSpinner();
          this.alertService.error('Incorrect credentials!');
        }
      });
  }

  @HostListener('document:keydown', ['$event']) onKeydownHandler(event: KeyboardEvent) {
    if (event.key === 'Enter') {
      this.login();
    }
  }

}
