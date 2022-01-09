import { Component, OnInit } from '@angular/core';
import {ReservationEntity} from "./model/reservation-entity";
import {ReservationEntityService} from "./service/reservation-entity.service";
import {ActivatedRoute} from "@angular/router";
import {HttpParams} from "@angular/common/http";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  entities: ReservationEntity[] = [];

  entityType: null;

  constructor(private reservationEntityService: ReservationEntityService, private route: ActivatedRoute) { }

  ngOnInit(): void {

    this.entityType = this.route.snapshot.data.entity;
    console.log(this.entityType);
    if (!this.entityType) {
      this.reservationEntityService.query().subscribe(response => {
        this.entities = response.body!;
      })
    } else {
      const params = new HttpParams().set('type', this.entityType!)
      this.reservationEntityService.queryAll(params).subscribe(response => {
        this.entities = response.body!;
      })
    }

  }

}
