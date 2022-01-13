import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth/auth.service';
import { ReservationService } from '../reservation/reservation.service';

@Component({
  selector: 'app-all-reservation',
  templateUrl: './all-reservation.component.html',
  styleUrls: ['./all-reservation.component.css']
})
export class AllReservationComponent implements OnInit {

  list:any[] = [];


  constructor(private reservationService:ReservationService, private authService:AuthService) {}

  ngOnInit(): void {
    this.reservationService.getAllReservationByUser(this.authService.getId()).subscribe((res:any)=>{
      for(let action of res){
        let startTime = action.startTime[0] + ":" + action.startTime[1];
        let endtime = action.endTime[0] + ":" + action.endTime[1];
        action.startTime = startTime;
        action.endTime = endtime;
        action.startDate = new Date(action.startDate).toLocaleDateString();
        action.endDate = new Date(action.endDate).toLocaleDateString();
        this.list.push(action);
      }
    })
  }

  decline = (id:any) =>{
    this.reservationService.delete(id).subscribe((res)=>{
      this.list = [];
      this.reservationService.getAllReservationByUser(this.authService.getId()).subscribe((res:any)=>{
        for(let action of res){
          let startTime = action.startTime[0] + ":" + action.startTime[1];
          let endtime = action.endTime[0] + ":" + action.endTime[1];
          action.startTime = startTime;
          action.endTime = endtime;
          action.startDate = new Date(action.startDate).toLocaleDateString();
          action.endDate = new Date(action.endDate).toLocaleDateString();
          this.list.push(action);
        }
      })
      alert("Successfuly");
    })

  }

}
