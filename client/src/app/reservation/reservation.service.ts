import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {map} from 'rxjs/operators';
import {AbstractRestService} from "../shared/abstract-rest.service";

@Injectable({
  providedIn: 'root'
})
export class ReservationService extends AbstractRestService<any> {


  constructor(
    public http: HttpClient
  ) {
    super(http, '/api/reservations');

  }


  createRevision(data: any) {
    return this.http.post("http://localhost:8080/api/reservations/revision", data);
  }


  createComplaint(data: any) {
    return this.http.post("http://localhost:8080/api/reservations/complaint", data);
  }

  searchAdventure(data: any) {
    return this.http.post("http://localhost:8080/api/availableDays/searchAdventure", data)
      .pipe(map(res => {
        return res;
      }));
  }

  searchShip(data: any) {
    return this.http.post("http://localhost:8080/api/availableDays/searchShip", data)
      .pipe(map(res => {
        return res;
      }));
  }


  searchCottage(data: any) {
    return this.http.post("http://localhost:8080/api/availableDays/searchCottage", data)
      .pipe(map(res => {
        return res;
      }));
  }

  createR(data: any) {
    return this.http.post("http://localhost:8080/api/reservations", data)
      .pipe(map(res => {
        return res;
      }));
  }

  reservAction(data: any) {
    return this.http.post("http://localhost:8080/api/reservations/action", data)
      .pipe(map(res => {
        return res;
      }));
  }

  getAllAction(id: any) {
    return this.http.get("http://localhost:8080/api/reservations/action/" + id)
      .pipe(map(res => {
        return res;
      }));
  }

  getAllReservationByUser(id: any) {
    return this.http.get("http://localhost:8080/api/reservations/users/" + id)
      .pipe(map(res => {
        return res;
      }));
  }


  delete(id: any) {
    return this.http.delete("http://localhost:8080/api/reservations/" + id)
      .pipe(map(res => {
        return res;
      }));
  }


}
