import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CodeDetailsComponent} from './restricted/code/code-details/code-details.component';
import {CodeComponent} from './restricted/code/code.component';
import {UserDetailsComponent} from './restricted/user/user-details/user-details.component';
import {HomeComponent} from './restricted/home/home.component';
import {LoginComponent} from './public/login/login.component';
import {UnauthorizedComponent} from './public/unauthorized/unauthorized.component';
import {UserComponent} from './restricted/user/user.component';
import {AuthGuardService} from './core/auth/auth-guard.service';
import {AdminGuardService} from './core/auth/admin-guard.service';

const routes: Routes = [

  {path: 'user', component: UserComponent, canActivate: [AuthGuardService]},
  {path: 'user/details', component: UserDetailsComponent, canActivate: [AuthGuardService]},
  {path: 'user/details/:id', component: UserDetailsComponent, canActivate: [AuthGuardService]},
  {path: 'code', component: CodeComponent, canActivate: [AuthGuardService]},
  {path: 'code/user/:id', component: CodeComponent, canActivate: [AuthGuardService]},
  {path: 'code/details', component: CodeDetailsComponent, canActivate: [AuthGuardService]},
  {path: 'code/details/:id', component: CodeDetailsComponent, canActivate: [AuthGuardService]},
  {path: 'home', component: HomeComponent, canActivate: [AuthGuardService ]},
  {path: 'login', component: LoginComponent},
  {path: 'unauthorized', component: UnauthorizedComponent},
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
