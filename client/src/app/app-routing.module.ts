import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { RegistrationComponent } from './auth/registration/registration.component';
import { VerificationRequestComponent } from './administrator/verification-request/verification-request.component';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';
import { AddAdminComponent } from './add-admin/add-admin.component';
import { ChangePasswordComponent } from './change-password/change-password.component';
import { DeleteUsersComponent } from './administrator/delete-users/delete-users.component';
import { DeleteEntitiesComponent } from './administrator/delete-entities/delete-entities.component';
import { ReservationComponent } from './reservation/reservation.component';
import { DeleteAccountRequestsComponent } from './administrator/delete-account-requests/delete-account-requests.component';
import { IncomeComponent } from './administrator/income/income.component';
import {ReservationPageComponent} from './reservation-page/reservation-page.component'
import {AllReservationComponent} from './all-reservation/all-reservation.component'
import {EndedReservationsComponent} from './ended-reservations/ended-reservations.component'
import {AdminReservationComponent} from "./admin-reservation/admin-reservation.component";
import { ViewComentsComponent } from './view-coments/view-coments.component';
import { ViewPenaltiesComponent } from './view-penalties/view-penalties.component';
import { LoyaltyProgramComponent } from './loyalty-program/loyalty-program.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
  },
  {
    path: 'home',
    component: HomeComponent,
  },
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'registration',
    component: RegistrationComponent,
  },
  {
    path: 'verificationRequests',
    component: VerificationRequestComponent,
  },
  {
    path: 'profile/:id',
    component: ProfileComponent,
  },
  {
    path: 'addAdmin',
    component: AddAdminComponent,
  },
  {
    path: 'changePassword',
    component: ChangePasswordComponent,
  },
  {
    path: 'deleteUsers',
    component: DeleteUsersComponent,
  },
  {
    path: 'deleteEntities',
    component: DeleteEntitiesComponent,
  },
  {
    path: 'reservation',
    component: ReservationComponent,
  },
  {
    path: 'deleteAccReq',
    component: DeleteAccountRequestsComponent,
  },
  {
    path: 'income',
    component: IncomeComponent,
  },
  {
    path: 'action/:id',
    component: ReservationPageComponent,
  },
  {
    path: 'all-reservation',
    component: AllReservationComponent,
  }, 
  {
    path: 'ended-reservation',
    component: EndedReservationsComponent,
  },
  {
    path: 'admin-reservation',
    component: AdminReservationComponent
  },
  {
    path: 'view-comments',
    component: ViewComentsComponent
  },
  {
    path: 'view-penalties',
    component: ViewPenaltiesComponent
  },
  {
    path: 'loyalty-program',
    component: LoyaltyProgramComponent
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
