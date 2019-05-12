import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {UserComponent} from "./restricted/user/user.component";
import {AuthGuardService} from "./core/auth/auth-guard.service";
import {UserDetailsComponent} from "./restricted/user/user-details/user-details.component";
import {HomeComponent} from "./restricted/home/home.component";
import {PrizeComponent} from "./restricted/prize/prize.component";
import {AnalystGuardService} from "./core/auth/analyst-guard.service";
import {LoginComponent} from "./public/login/login.component";
import {UnauthorizedComponent} from "./public/unauthorized/unauthorized.component";
import {PrizeDetailsComponent} from "./restricted/prize/prize-details/prize-details.component";
import {QuickGuideComponent} from "./restricted/quick-guide/quick-guide.component";

const routes: Routes = [

  {path: 'user', component: UserComponent, canActivate: [AuthGuardService]},
  {path: 'user/details', component: UserDetailsComponent, canActivate: [AuthGuardService]},
  {path: 'user/details/:id', component: UserDetailsComponent, canActivate: [AuthGuardService]},

  {path: 'prize', component: PrizeComponent, canActivate: [AuthGuardService]},
  {path: 'prize/details', component: PrizeDetailsComponent, canActivate: [AuthGuardService, AnalystGuardService]},
  {path: 'prize/details/:id', component: PrizeDetailsComponent, canActivate: [AuthGuardService, AnalystGuardService]},
  
  {path: 'home', component: HomeComponent, canActivate: [AuthGuardService]},
  
  {path: 'quick_guide', component: QuickGuideComponent},
  {path: 'login', component: LoginComponent},
  {path: 'unauthorized', component: UnauthorizedComponent, canActivate: [AuthGuardService]},

  {path: '', component: LoginComponent}
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ],
  declarations: []
})
export class AppRoutingModule {

}
