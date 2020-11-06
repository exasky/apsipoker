import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {LoginComponent} from './login/login.component';
import {AuthGuard} from './login/guard/auth.guard';
import {UserDetailComponent} from './user/component/user-detail.component';
import {UserListComponent} from './user/component/user-list.component';
import {ProfileGuard} from './login/guard/profile.guard';
import {ROLE_ADMIN} from './user/user';

const routes: Routes = [
  {path: 'login', component: LoginComponent},

  {
    path: '', canActivate: [AuthGuard], children: [
      {path: 'users', canActivate: [ProfileGuard], data: {roles: [ROLE_ADMIN]}, component: UserListComponent},
      {path: 'user-detail', component: UserDetailComponent}
    ]
  },

  // otherwise redirect to home
  {path: '**', redirectTo: ''}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
