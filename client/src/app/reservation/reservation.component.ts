import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth/auth.service';
import { ReservationService } from './reservation.service';

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css']
})

export class ReservationComponent implements OnInit {
  adventrueFlag:boolean = false
  boatFlag:boolean = false;
  cottageFlag:boolean = false;
  listOfEntity: any[] = [];
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
  constructor(private reservatioService: ReservationService, private authServie: AuthService) { }

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
    this.reservationSearch.startDate = new Date(event.target.valueAsNumber);
  }


  changeTimeAdventure = (event:any) =>{
    let time : string = event.target.value;
    this.reservationAdventure.startTime = time;
    this.reservationSearch.startTime = time;
  }

  searachEntity = () => {
    if(this.adventrueFlag){
      this.reservatioService.searchAdventure(this.reservationSearch).subscribe(
        (res:any) => {
          console.log(res);
         this.listOfEntity = res;
        }
      )
    }
    if(this.cottageFlag){
      this.reservatioService.searchCottage(this.reservationSearch).subscribe(
        (res:any) => {
          console.log(res);
          this.listOfEntity = res;
        }
      )
    }
    if(this.boatFlag){
      this.reservatioService.searchShip(this.reservationSearch).subscribe(
        (res:any) => {
          console.log(res);
          this.listOfEntity = res;
        }
      )
    }

  }


  reservEtity = (id:any) =>{
    const a:any = this.authServie.getId();
    this.reservationAdventure.userId = a.toString() ;
    this.reservationAdventure.reservationEntityId = id;
    this.reservatioService.create(this.reservationAdventure).subscribe(
      (res:any) => {
        console.log(res);
        this.listOfEntity = res;
      }
    )
  }


}
