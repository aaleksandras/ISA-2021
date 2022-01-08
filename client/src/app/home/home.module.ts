import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {HomeRoutingModule} from './home-routing.module';
import {HomeComponent} from './home.component';
import {SharedModule} from "../shared/shared.module";
import { ReservationEntityListComponent } from './reservation-entity-list/reservation-entity-list.component';
import { CardComponent } from './reservation-entity-list/card/card.component';


@NgModule({
  declarations: [
    HomeComponent,
    ReservationEntityListComponent,
    CardComponent,
  ],
  imports: [
    CommonModule,
    HomeRoutingModule,
    SharedModule
  ]
})
export class HomeModule {
}
