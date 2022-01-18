import { Component, OnInit } from '@angular/core';
import {ReservationService} from "../reservation/reservation.service";
import {AuthService} from "../auth/auth.service";

@Component({
  selector: 'app-admin-reservation',
  templateUrl: './admin-reservation.component.html',
  styleUrls: ['./admin-reservation.component.css']
})
export class AdminReservationComponent implements OnInit {

  list:any[] = [];


  constructor(private reservationService:ReservationService, private authService:AuthService) {}

  ngOnInit(): void {
    this.reservationService.queryAll().subscribe(response => {
      this.list = []!;
      for(let action of response.body!){
        let startTime = action.startTime[0] + ":" + action.startTime[1];
        let endtime = action.endTime[0] + ":" + action.endTime[1];
        action.startTime = startTime;
        action.endTime = endtime;
        action.startDate = new Date(action.startDate).toISOString();
        action.endDate = new Date(action.endDate).toISOString();
        this.list.push(action);
      }
    })
  }

  sendAnswer(item: any) {
    this.reservationService.createComplaint({
      id: item.id,
      complaint: item.complaint,
      answer: item.answer,
      status: 1
    }).subscribe(response => {
      this.reservationService.queryAll().subscribe(response => {
        alert("Odgovor se poslao!")
        this.list = []!;
        for(let action of response.body!){
          let startTime = action.startTime[0] + ":" + action.startTime[1];
          let endtime = action.endTime[0] + ":" + action.endTime[1];
          action.startTime = startTime;
          action.endTime = endtime;
          action.startDate = new Date(action.startDate).toISOString();
          action.endDate = new Date(action.endDate).toISOString();
          this.list.push(action);
        }
      })
    });




  }
  accept(item: any) {
    this.reservationService.createRevision({
      id: item.id,
      revision: item.revision,
      mark: item.mark,
      status: 1
    }).subscribe(response => {
      this.reservationService.queryAll().subscribe(response => {
        alert("Prihvacena revizija!")
        this.list = []!;
        for(let action of response.body!){
          let startTime = action.startTime[0] + ":" + action.startTime[1];
          let endtime = action.endTime[0] + ":" + action.endTime[1];
          action.startTime = startTime;
          action.endTime = endtime;
          action.startDate = new Date(action.startDate).toISOString();
          action.endDate = new Date(action.endDate).toISOString();
          this.list.push(action);
        }
      })
    });
  }

  decline(item: any) {
    this.reservationService.createRevision({
      id: item.id,
      revision: item.revision,
      mark: item.mark,
      status: 0
    }).subscribe(response => {
      this.reservationService.queryAll().subscribe(response => {
        alert("OOdbijena revizija!")
        this.list = []!;
        for(let action of response.body!){
          let startTime = action.startTime[0] + ":" + action.startTime[1];
          let endtime = action.endTime[0] + ":" + action.endTime[1];
          action.startTime = startTime;
          action.endTime = endtime;
          action.startDate = new Date(action.startDate).toISOString();
          action.endDate = new Date(action.endDate).toISOString();
          this.list.push(action);
        }
      })
    });
  }
}
