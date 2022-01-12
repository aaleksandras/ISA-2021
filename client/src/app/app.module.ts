import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AuthModule } from './auth/auth.module';
import { NavbarModule } from './navbar/navbar.module';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { TokenInterceptor } from './interceptor/TokenInterceptor';
import { HttpClientModule } from '@angular/common/http';
import {SharedModule} from "./shared/shared.module";
import {HomeModule} from "./home/home.module";
import { VerificationRequestComponent } from './administrator/verification-request/verification-request.component';
import { ProfileComponent } from './profile/profile.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AddAdminComponent } from './add-admin/add-admin.component';
import { ChangePasswordComponent } from './change-password/change-password.component';
import { DeleteUsersComponent } from './administrator/delete-users/delete-users.component';
import { DeleteEntitiesComponent } from './administrator/delete-entities/delete-entities.component';
import { ReservationComponent } from './reservation/reservation.component';
import { DeleteAccountRequestsComponent } from './administrator/delete-account-requests/delete-account-requests.component';
import { IncomeComponent } from './administrator/income/income.component';
@NgModule({
  declarations: [AppComponent, VerificationRequestComponent, ProfileComponent, AddAdminComponent, ChangePasswordComponent, DeleteUsersComponent, DeleteEntitiesComponent, ReservationComponent, DeleteAccountRequestsComponent, IncomeComponent],
  imports: [
    HttpClientModule,
    BrowserModule,
    AppRoutingModule,
    AuthModule,
    NavbarModule,
    SharedModule,
    HomeModule,
    ReactiveFormsModule,
    FormsModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true,
    },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
