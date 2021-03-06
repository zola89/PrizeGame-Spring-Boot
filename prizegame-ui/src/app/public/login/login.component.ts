import {Component, HostListener} from '@angular/core';
import {Router} from '@angular/router';
import {AuthService} from '../../core/auth/auth.service';
import {AlertService} from '../../core/alert/alert-service';
import {User} from '../../restricted/user/user.model';
import {isNullOrUndefined} from 'util';
import {UserService} from '../../restricted/user/user.service';
import { AppHeaderMessengerService } from 'src/app/header/app-header-messager.service';
import { UserStorage } from 'src/app/core/auth/user.storage';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  name: string;
  email: string;
  password: string;
  user: User;
  signUp = false;

  constructor(private router: Router,
              private authService: AuthService,
              private alertService: AlertService,
              private userService: UserService,
              public userStorage: UserStorage, 
              private appHeaderMessengerService: AppHeaderMessengerService) {

  }

  login(): void {

    if (this.signUp === true) {
      this.register();
      return;
    }

    this.authService.attemptAuth(this.email, this.password).subscribe(
      data => {
        this.user = data;
        this.signUp = false;
        
        if (!isNullOrUndefined(this.user)) { 
          this.appHeaderMessengerService.refresh(true);
          this.userStorage.saveCurrentUser(JSON.stringify(this.user));
  
          if(this.user.user_role === "ADMIN") {
            this.router.navigateByUrl('user');
          } else {
            this.router.navigateByUrl('home');
          }
          
        }

      },
      err => {
        if (err.status === 500) {
          this.alertService.hideSpinner();
          this.alertService.error('Incorrect credentials!');
        }
      });
  }

  register(): void {
    if (this.signUp === false) {
      this.signUp = true;
      return;
    }
    const user = new User();
    user.name = this.name;
    user.email = this.email;
    user.password = this.password;

    this.userService.save(user).subscribe(
        data => {
          this.signUp = false;
          this.appHeaderMessengerService.refresh(true);
          this.router.navigateByUrl('home');
        }
    );

  }


  @HostListener('document:keydown', ['$event']) onKeydownHandler(event: KeyboardEvent) {
    if (event.key === 'Enter') {
      this.login();
    }
  }

}
