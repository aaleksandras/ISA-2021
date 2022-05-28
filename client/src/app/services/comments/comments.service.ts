import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Penalty } from '../../model/PenaltyModel';

@Injectable({
  providedIn: 'root'
})
export class ComentsService {

  constructor(private http: HttpClient) {}

  getComments = (id: any) => {
    console.log("poslato je")
     return this.http.get('http://localhost:8080/api/user/getComments/'+id);//.pipe(
    //   map((responseData: any) => {
    //     console.log(responseData);
    //     return responseData;
    //   })
    // );
  };
  sendPenalty = (id: any,penalty: Penalty) => {
    return this.http.post('http://localhost:8080/api/user/addPenalty/',penalty ).pipe(
      map((responseData: any) => {
        return responseData;
      })
    );
  };
  getPenalties = (id: any) => {
    return this.http.get('http://localhost:8080/api/user/getPenalties/'+id).pipe(
      map((responseData: any) => {
        return responseData;
      })
    );
  };
  approvePenalty = (id: any, penaltyId: any) => {
    return this.http.get('http://localhost:8080/api/user/acc/'+penaltyId).pipe(
      map((responseData: any) => {
        return responseData;
      })
    );
  };
  deletePenalty = (id: any, penaltyId:any) => {
    return this.http.get('http://localhost:8080/api/user/del/'+penaltyId).pipe(
      map((responseData: any) => {
        return responseData;
      })
    );
  };
  getMyPenalties =  (id: any) => {
    return this.http.get('http://localhost:8080/api/user/getMyPenalty/'+id).pipe(
      map((responseData: any) => {
        return responseData;
      })
    );
  };
}
