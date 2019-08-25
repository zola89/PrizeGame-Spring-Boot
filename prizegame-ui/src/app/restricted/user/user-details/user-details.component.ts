import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {FormControl, Validators} from '@angular/forms';
import {User} from '../user.model';
import {UserService} from '../user.service';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.scss']
})
export class UserDetailsComponent implements OnInit {

  id: any;
  user: User = new User();
  check = false;
  roles = ['ADMIN', 'USER'];

  formControl = new FormControl('', [
    Validators.required,
    Validators.email
  ]);

  constructor(private router: Router, protected route: ActivatedRoute, private userService: UserService) {
  }

  ngOnInit(): void {

    this.route.params.subscribe(params => {
      this.id = params['id'];
    });

    if (this.id) {
      this.userService.getById(this.id).subscribe(
        data => {
          this.user = data;
        }
      );
    }
    this.check = false;
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

    this.userService.save(this.user).subscribe(
      data => {
        this.navigateBack();
      }
    );
  }

  public onUpdate(): void {
    this.userService.update(this.id, this.user).subscribe(
      data => {
        this.navigateBack();
      }
    );
  }

  changeState() {
    this.check = !this.check;
  }

  navigateBack() {
      this.router.navigateByUrl('user');
  }

}
