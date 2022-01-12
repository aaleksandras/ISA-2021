import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {


  constructor(
    private http: HttpClient
  ) { }

  searchAdventure(data:any) {
    return this.http.post("http://localhost:8080/api/availableDays/searchAdventure", data)
      .pipe(map(res => {
        return res;
      }));
  }

  searchShip(data:any) {
    return this.http.post("http://localhost:8080/api/availableDays/searchShip", data)
      .pipe(map(res => {
        return res;
      }));
  }


  searchCottage(data:any) {
    return this.http.post("http://localhost:8080/api/availableDays/searchCottage", data)
      .pipe(map(res => {
        return res;
      }));
  }

  create(data:any) {
    return this.http.post("http://localhost:8080/api/reservations", data)
      .pipe(map(res => {
        return res;
      }));
  }

  reservAction(data:any) {
    return this.http.post("http://localhost:8080/api/reservations/action", data)
      .pipe(map(res => {
        return res;
      }));
  }

  getAllAction(id:any) {
    return this.http.get("http://localhost:8080/api/reservations/action/" + id)
      .pipe(map(res => {
        return res;
      }));
  }

}
