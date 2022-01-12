import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ReservationService } from '../reservation/reservation.service';
import {AuthService} from '../auth/auth.service'

@Component({
  selector: 'app-reservation-page',
  templateUrl: './reservation-page.component.html',
  styleUrls: ['./reservation-page.component.css']
})
export class ReservationPageComponent implements OnInit {
  reservationEntity:any;
  list:any[] = [];
  constructor(  
    private route: ActivatedRoute, 
    private reservationService:ReservationService,
    private authService: AuthService) { }

  ngOnInit(): void {
    this.reservationEntity = this.route.snapshot.params[`id`];
    this.reservationService.getAllAction(this.reservationEntity).subscribe((res:any) =>{
      this.list = res;
    })
  }


  bookAction = (reservationId:any) =>{
    let a = this.authService.getId();
    let r = {
      "userId": a,
      "reservationId": reservationId
    } 
    this.reservationService.reservAction(r).subscribe(
      (res:any) => {
        alert("Successfully")
      }, error =>{
        alert("Error");
      })
  }

}
