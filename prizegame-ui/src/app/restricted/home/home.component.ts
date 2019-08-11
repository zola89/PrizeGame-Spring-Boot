import {Component, OnDestroy, OnInit} from '@angular/core';
import {Code} from '../code/code.model';
import {User} from '../user/user.model';
import {Router} from '@angular/router';

@Component({
  selector: 'home',
  templateUrl: './home.component.html',
  styleUrls: ['home.component.css']
})
export class HomeComponent implements OnInit, OnDestroy {

  code = new Code();
  user = new User();

  constructor(private router: Router) {
  }

  ngOnInit() {
    //TODO get currently logged in user
  }

  ngOnDestroy() {
  }

  getPrize() {
    this.code.prizeType = 'CASH'; //TODO create Enum PrizeType and than get random PrizeType
    console.log(this.code);
    this.user.codes.push(this.code);
  }

  getErrorMessage() {

  }

  viewPrizes() {
    this.router.navigateByUrl('code');
  }

}
