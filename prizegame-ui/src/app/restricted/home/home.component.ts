import {Component, OnDestroy, OnInit} from '@angular/core';
import {Code} from '../code/code.model';
import {User} from '../user/user.model';
import {Router} from '@angular/router';
import {CodeService} from '../code/code.service';
import {FormControl, Validators} from '@angular/forms';
import {UserStorage} from '../../core/auth/user.storage';
import {isNullOrUndefined} from 'util';

@Component({
  selector: 'home',
  templateUrl: './home.component.html',
  styleUrls: ['home.component.css']
})
export class HomeComponent implements OnInit, OnDestroy {

  code = new Code();
  user = new User();
  won = false;

  formControl = new FormControl('', [
    Validators.required,
    Validators.nullValidator
  ]);

  constructor(private router: Router, private codeService: CodeService, private userStorage: UserStorage) {
  }

  ngOnInit() {
    this.user = this.userStorage.getCurrentUser();
  }

  ngOnDestroy() {
  }

  getPrize() {
    if (this.code.prize_code) {

      this.codeService.getByPrizeCode(this.code.prize_code).subscribe(
          data => {

            if (data !== null) {
              this.code = data;
              this.code.user_id = this.user.id;

              if (isNullOrUndefined(this.user.codes)) {
                this.user.codes = [];
              }
              this.user.codes.push(this.code);

              this.codeService.update(this.code.id, this.code).subscribe(
                  data1 => {
                    this.won = true;
                  }
              );

            } else {
              this.code.prize_code = null;
              this.formControl.setErrors({nullValidator: true});
              this.getErrorMessage();
            }

          },
          error1 => {
            this.formControl.setErrors({nullValidator: true});
            this.getErrorMessage();
          }
      );
    }
  }

  getErrorMessage() {
    return this.formControl.hasError('required') ? 'Required field' :
        this.formControl.hasError('nullValidator') ? 'Not a valid code' : '';
  }

  viewPrizes() {
    this.router.navigateByUrl('code/user/' + this.user.id);
  }

  addPrize() {
    this.won = false;
    this.code = new Code();
    this.router.navigateByUrl('home');
  }

}
