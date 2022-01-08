import {Component, Input, OnInit} from '@angular/core';
import {ReservationEntity} from "../../model/reservation-entity";

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {

  @Input() entity!: ReservationEntity;
  constructor() { }

  ngOnInit(): void {
  }

}
