import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from "./auth/login/login.component";
import { RegistrationComponent } from "./auth/registration/registration.component";
import { VerificationRequestComponent } from './administrator/verification-request/verification-request.component';
import { HomeComponent } from "./home/home.component";
import { ProfileComponent } from './profile/profile.component';
import { AddAdminComponent } from './add-admin/add-admin.component';
import { ChangePasswordComponent } from './change-password/change-password.component';
import { DeleteUsersComponent } from './administrator/delete-users/delete-users.component';

const routes: Routes = [
  {
    path: 'home', component: HomeComponent
  },
  {
    path: 'login', component: LoginComponent
  },
  {
    path: 'registration', component: RegistrationComponent
  },
  {
    path: 'verificationRequests', component: VerificationRequestComponent
  },
  {
    path: 'profile/:id', component: ProfileComponent
  },
  {
    path: 'addAdmin', component: AddAdminComponent
  },
  {
    path: 'changePassword', component: ChangePasswordComponent
  },
  {
    path: 'deleteUsers', component: DeleteUsersComponent
  }
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
