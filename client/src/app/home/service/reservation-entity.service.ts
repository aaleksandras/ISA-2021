import { Injectable } from '@angular/core';
import {AbstractRestService} from "../../shared/abstract-rest.service";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ReservationEntityService extends AbstractRestService<any>{

  constructor(public http: HttpClient) {
    super(http, '/api/reservation-entity');
  }
}
