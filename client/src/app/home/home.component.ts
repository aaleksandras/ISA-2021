import { Component, OnInit } from '@angular/core';
import {ReservationEntity} from "./model/reservation-entity";
import {ReservationEntityService} from "./service/reservation-entity.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  entities: ReservationEntity[] = [];

  constructor(private reservationEntityService: ReservationEntityService) { }

  ngOnInit(): void {
    this.reservationEntityService.query().subscribe(response => {
      this.entities = response.body!;
    })
  }

}
