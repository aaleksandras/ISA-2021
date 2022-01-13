import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-reservation-entity-list',
  templateUrl: './reservation-entity-list.component.html',
  styleUrls: ['./reservation-entity-list.component.css']
})
export class ReservationEntityListComponent implements OnInit {

  @Input() items: any;

  constructor() { }

  ngOnInit(): void {
  }

}
