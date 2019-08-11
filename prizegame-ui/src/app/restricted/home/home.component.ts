import {Component, OnDestroy, OnInit} from '@angular/core';
import {Code} from '../code/code.model';
import {User} from '../user/user.model';
import {Router} from '@angular/router';
import {CodeService} from '../code/code.service';
import {FormControl, ValidationErrors, Validators} from '@angular/forms';

@Component({
  selector: 'home',
  templateUrl: './home.component.html',
  styleUrls: ['home.component.css']
})
export class HomeComponent implements OnInit, OnDestroy {

  code = new Code();
  user = new User();



  formControl = new FormControl('', [
    Validators.required,
    Validators.nullValidator
  ]);

  constructor(private router: Router, private codeService: CodeService) {
  }

  ngOnInit() {
  }

  ngOnDestroy() {
  }

  getPrize() {
    if (this.code.prizeCode) {
      this.codeService.getByPrizeCode(this.code.prizeCode).subscribe(
          data => {

            if (data !== null) {
              this.code = data;
              this.user.codes.push(this.code);
            } else {
              this.code.prizeCode = null;
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
    this.router.navigateByUrl('code');
  }

  addPrize() {
    this.code = new Code();
    this.router.navigateByUrl('home');
  }

}
