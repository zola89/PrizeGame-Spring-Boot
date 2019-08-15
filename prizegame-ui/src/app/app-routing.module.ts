import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CodeDetailsComponent} from './restricted/code/code-details/code-details.component';
import {CodeComponent} from './restricted/code/code.component';
import {UserDetailsComponent} from './restricted/user/user-details/user-details.component';
import {HomeComponent} from './restricted/home/home.component';
import {LoginComponent} from './public/login/login.component';
import {UnauthorizedComponent} from './public/unauthorized/unauthorized.component';
import {UserComponent} from './restricted/user/user.component';

const routes: Routes = [

  {path: 'user', component: UserComponent},
  {path: 'user/details', component: UserDetailsComponent},
  {path: 'user/details/:id', component: UserDetailsComponent},
  {path: 'code', component: CodeComponent},
  {path: 'code/user/:id', component: CodeComponent},
  {path: 'code/details', component: CodeDetailsComponent},
  {path: 'code/details/:id', component: CodeDetailsComponent},
  {path: 'home', component: HomeComponent},
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
