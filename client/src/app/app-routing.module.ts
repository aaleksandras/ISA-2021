import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from "./auth/login/login.component";
import { RegistrationComponent } from "./auth/registration/registration.component";
import { VerificationRequestComponent } from './administrator/verification-request/verification-request.component';
import {HomeComponent} from "./home/home.component";
import { ProfileComponent } from './profile/profile.component';


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
  }
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
