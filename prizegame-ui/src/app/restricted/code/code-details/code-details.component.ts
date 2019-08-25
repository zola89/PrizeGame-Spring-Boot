import {Component, OnInit} from '@angular/core';
import {Code} from '../code.model';
import {ActivatedRoute, Router} from '@angular/router';
import {FormControl, Validators} from '@angular/forms';
import {CodeService} from '../code.service';


@Component({
  selector: 'app-prize-details',
  templateUrl: './code-details.component.html',
  styleUrls: ['./code-details.component.sass']
})
export class CodeDetailsComponent implements OnInit {

  id: any;
  code: Code = new Code();
  prizeTypes = ['CASH', 'GIFT_CARD', 'HOLIDAY', 'MOBILE_PHONE', 'SHIRT'];

  formControl = new FormControl('', [
    Validators.required
  ]);

  constructor(private router: Router, protected route: ActivatedRoute, private codeService: CodeService) {
  }

  ngOnInit(): void {

    this.route.params.subscribe(params => {
      this.id = +params['id'];
    });


    if (this.id) {
      this.codeService.getById(this.id).subscribe(
          data => {
            this.code = data;
          }
      );
    }
  }

  getErrorMessage() {
    return this.formControl.hasError('required') ? 'Required field' :
        this.formControl.hasError('email') ? 'Not a valid email' : '';
  }

  getNotEqualPasswordMessage() {
    return this.formControl.hasError('notEqual') ? 'Required field' :
        this.formControl.hasError('required') ? 'Password does not match' : '';
  }

  submit() {
    if (this.id) {
      this.onUpdate();
    } else {
      this.onSave();
    }
  }

  onCancel(): void {
    this.navigateBack();
  }

  public onSave(): void {

    this.code.prizeTime = new Date();

    this.codeService.save(this.code).subscribe(
        data => {
          this.navigateBack();
        }
    );
  }

  public onUpdate(): void {
    this.codeService.update(this.id, this.code).subscribe(
        data => {
          this.navigateBack();
        }
    );
  }

  navigateBack() {
    this.router.navigateByUrl('code');
  }


}
