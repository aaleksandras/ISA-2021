import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css']
})
export class ReservationComponent implements OnInit {
  adventrueFlag:boolean = false
  boatFlag:boolean = false;
  cottageFlag:boolean = false;
  reservationAdventure = {
    startDate: new Date,
    startTime:'',
    numberOfDay: 0,
    reservationEntityId: '',
    userId: '',
    type:1
  }

  dateStartAdventure:any;
  timeStrAdventure:any;
  constructor() { }

  ngOnInit(): void {
  }


  startAdventureReservation = () => {
    this.adventrueFlag = true;
    this.boatFlag = false;
    this.cottageFlag = false;
  }

  startBoatReservation = () => {
    this.boatFlag = true;
    this.adventrueFlag = false;
    this.cottageFlag = false;
    
  }

  startCotaggeReservation = () => {
    this.cottageFlag = true;
    this.boatFlag = false;
    this.adventrueFlag = false;
  }

  changeDateAdventure(event:any){
    this.reservationAdventure.startDate = new Date(event.target.valueAsNumber);
    this.dateStartAdventure = event.target.value;
  }


  changeTimeAdventure = (event:any) =>{
    let time : string = event.target.value;
    this.reservationAdventure.startTime = time;
  }


}
