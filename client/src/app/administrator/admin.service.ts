import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AdminService {


  constructor(private http: HttpClient) { }

  getUnverified = () => {
    return this.http
      .get('http://localhost:8080/api/user/getUnverified')
      .pipe(
        map((responseData: any) => {
          return responseData;
        })
      );
  };

  verifyAccount = (id: string) => {
    return this.http
      .get('http://localhost:8080/api/user/activateAccount/' + id)
      .pipe(
        map((responseData: any) => {
          return responseData;
        })
      );
  };

  declineAccount = (id: string) => {
    return this.http
      .get('http://localhost:8080/api/user/declineAccount/' + id)
      .pipe(
        map((responseData: any) => {
          return responseData;
        })
      );
  };
}
