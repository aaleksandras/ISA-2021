import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomeRoutingModule } from './home-routing.module';
import { RegistrationComponent } from './registration/registration.component';
import { HomeComponent } from './home/home.component';


@NgModule({
  declarations: [
    RegistrationComponent,
    HomeComponent
  ],
  imports: [
    CommonModule,
    HomeRoutingModule
  ]
})
export class HomeModule { }
