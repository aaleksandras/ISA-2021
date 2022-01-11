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

  reservationSearch = {
    startDate: new Date,
    startTime: '',
    numberOfDay: 0,
    type: 0
  }

  dateStartAdventure:any;
  timeStrAdventure:any;
  constructor() { }

  ngOnInit(): void {
  }


  startAdventureReservation = () => {
    this.adventrueFlag = true;
    this.reservationSearch.type = 2;
    this.boatFlag = false;
    this.cottageFlag = false;
  }

  startBoatReservation = () => {
    this.boatFlag = true;
    this.reservationSearch.type = 0;
    this.adventrueFlag = false;
    this.cottageFlag = false;
    
  }

  startCotaggeReservation = () => {
    this.cottageFlag = true;
    this.reservationSearch.type = 1;
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
