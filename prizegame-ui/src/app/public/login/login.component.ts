import {Component, HostListener} from '@angular/core';
import {Router} from '@angular/router';
import {AuthService} from '../../core/auth/auth.service';
import {AlertService} from '../../core/alert/alert-service';
import {User} from '../../restricted/user/user.model';
import {isNullOrUndefined} from 'util';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  email: string;
  password: string;
  user: User;

  constructor(private router: Router,
              private authService: AuthService,
              private alertService: AlertService) {
  }

  login(): void {

    this.authService.attemptAuth(this.email, this.password).subscribe(
      data => {
        this.user = data;
        console.log(this.user);
        if (!isNullOrUndefined(this.user)) {
          sessionStorage.setItem('currentUser', JSON.stringify(this.user));
          this.router.navigateByUrl('home');
        }

      },
      err => {
        if (err.status === 500) {
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
