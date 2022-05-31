import { Injectable } from '@angular/core';
import {  LoyaltyProgramModel } from '../../model/LoyaltyProgramModel';
import {HttpClient, HttpResponse} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class LoyaltyProgramService {

  constructor(private http: HttpClient) {}

  defineProgram(t: LoyaltyProgramModel): Observable<HttpResponse<LoyaltyProgramModel>> {
    return this.http.post<LoyaltyProgramModel>('http://localhost:8080/api/user/defineLoyaltyProgram', t, { observe: 'response' });
  }

  getLoyaltyProgram(): Observable<HttpResponse<LoyaltyProgramModel>> {
    console.log('uslo u get')
    return this.http.get<LoyaltyProgramModel>('http://localhost:8080/api/user/getLoyaltyProgram', { observe: 'response' });
  }

}
