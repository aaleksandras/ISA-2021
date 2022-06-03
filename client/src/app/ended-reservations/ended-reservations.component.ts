import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth/auth.service';
import { ReservationService } from '../reservation/reservation.service';
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-ended-reservations',
  templateUrl: './ended-reservations.component.html',
  styleUrls: ['./ended-reservations.component.css']
})
export class EndedReservationsComponent implements OnInit {

  list:any[] = [];


  constructor(private reservationService:ReservationService, private authService:AuthService) {
    console.log("BBB")
  }

  ngOnInit(): void {
    console.log("AAA")
    this.reservationService.getEndedReservationByUser(this.authService.getId()).subscribe((res:any)=>{
      console.log(res)
      for(let action of res){
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

  decline = (id:any) =>{
    this.reservationService.delete(id).subscribe((res)=>{
      this.list = [];
      this.reservationService.getAllReservationByUser(this.authService.getId()).subscribe((res:any)=>{
        for(let action of res){
          let startTime = action.startTime[0] + ":" + action.startTime[1];
          let endtime = action.endTime[0] + ":" + action.endTime[1];
          action.startTime = startTime;
          action.endTime = endtime;
          action.startDate = new Date(action.startDate).toISOString();
          action.endDate = new Date(action.endDate).toISOString();
          this.list.push(action);
        }
      })
      alert("Successfuly");
    })

  }



  onSubmitComplaint(item: any) {
    if(item.complaint != null && item.complaint.length > 0) {
      this.reservationService.createComplaint({id: item.id, complaint: item.complaint, status: 0}).subscribe(response => {
        alert("Zalba poslata!");
        this.list = [];
        this.reservationService.getAllReservationByUser(this.authService.getId()).subscribe((res:any)=>{
          for(let action of res){
            let startTime = action.startTime[0] + ":" + action.startTime[1];
            let endtime = action.endTime[0] + ":" + action.endTime[1];
            action.startTime = startTime;
            action.endTime = endtime;
            action.startDate = new Date(action.startDate).toISOString();
            action.endDate = new Date(action.endDate).toISOString();
            this.list.push(action);
          }
        })
      })
    } else {
      alert("Morate uneti zalbu!")
    }

  }


  onSubmitRevision(item: any) {
    console.log(item);
    if (item.revision != null && item.mark != null && item.revision.length > 0 && parseInt(item.mark) > 0 && parseInt(item.mark) < 6 ) {
      this.reservationService.createRevision({id: item.id, userID:this.authService.getId(), revision: item.revision, mark: item.mark}).subscribe(response => {
        console.log(response);
        alert('Revizija sacuvana!');
        this.list = [];
        this.reservationService.getAllReservationByUser(this.authService.getId()).subscribe((res:any)=>{
          for(let action of res){
            let startTime = action.startTime[0] + ":" + action.startTime[1];
            let endtime = action.endTime[0] + ":" + action.endTime[1];
            action.startTime = startTime;
            action.endTime = endtime;
            action.startDate = new Date(action.startDate).toISOString();
            action.endDate = new Date(action.endDate).toISOString();
            this.list.push(action);
          }
        })
      })
    } else {
      alert("Morate uneti reviziju i ocenu od 1 do 5!");
    }

  }

}
