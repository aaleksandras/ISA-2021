import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth/auth.service';
import { ReservationService } from './reservation.service';
import { RegistrationService } from '../auth/registration.service';

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css']
})

export class ReservationComponent implements OnInit {
  user: any;
  adventrueFlag:boolean = false
  penalties : number = 0;

  brojDozvoljenih : number = 3;
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
    type: 0,
    userID: ''
  }

  dateStartAdventure:any;
  timeStrAdventure:any;
  constructor(private reservatioService: ReservationService, public authServie: AuthService, private registrationService: RegistrationService) { }

  ngOnInit(): void {
    const a:any = this.authServie.getId();
    this.registrationService.find(a).subscribe((res) => {
      this.user = res.body;
      this.penalties = this.user.penalties;
    });
  }


  startAdventureReservation = () => {
    this.adventrueFlag = true;
    this.reservationSearch.type = 2;
    this.boatFlag = false;
    this.cottageFlag = false;
    this.listOfEntity = [];
  }

  startBoatReservation = () => {
    this.boatFlag = true;
    this.reservationSearch.type = 0;
    this.adventrueFlag = false;
    this.cottageFlag = false;
    this.listOfEntity = [];

  }

  startCotaggeReservation = () => {
    this.cottageFlag = true;
    this.reservationSearch.type = 1;
    this.boatFlag = false;
    this.adventrueFlag = false;
    this.listOfEntity = [];
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
    const a:any = this.authServie.getId();
    this.reservationSearch.userID = a.toString();
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
    let tri = 3;
    let pen = this.penalties;

    this.reservationAdventure.userId = a.toString() ;
    this.reservationAdventure.reservationEntityId = id;
    this.reservationAdventure.numberOfDay = this.reservationSearch.numberOfDay;
    if(pen < tri){
      this.reservatioService.createR(this.reservationAdventure).subscribe(
        (res:any) => {
          alert("Successfuly")
        }, error =>{
          alert("Error");
        }
      )

    }else{
        alert("You cannot book an appointment, you have 3 or more penalties")
    }
   
  }
}
