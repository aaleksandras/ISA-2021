import {Component, Input, OnInit} from '@angular/core';
import {ReservationEntity} from "../model/reservation-entity";

@Component({
  selector: 'app-reservation-entity-list',
  templateUrl: './reservation-entity-list.component.html',
  styleUrls: ['./reservation-entity-list.component.css']
})
export class ReservationEntityListComponent implements OnInit {

  @Input() items: ReservationEntity[] = [];

  constructor() { }

  ngOnInit(): void {
  }

}
