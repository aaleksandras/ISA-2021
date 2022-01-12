import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { DeleteRequestModel } from '../model/DeleteRequestModel';
import { RegistrationModel } from '../model/RegistrationModel';
import { ReportModel } from '../model/ReportModel';
import { ResetPasswordModel } from '../model/ResetPasswordModel';

@Injectable({
  providedIn: 'root',
})
export class AdminService {
  generateReport(model: ReportModel) {
    return this.http.post('http://localhost:8080/api/user/getReport', model);
  }
  resetPassword(model: ResetPasswordModel) {
    return this.http.post(
      'http://localhost:8080/api/user/resetPassword',
      model
    );
  }

  constructor(private http: HttpClient) {}

  getUnverified = () => {
    return this.http.get('http://localhost:8080/api/user/getUnverified').pipe(
      map((responseData: any) => {
        return responseData;
      })
    );
  };

  getReservationPercentage = () => {
    return this.http
      .get('http://localhost:8080/api/user/getPercentageForReservations')
      .pipe(
        map((responseData: any) => {
          return responseData;
        })
      );
  };

  changeReservationPercentage = (newPercentage: string) => {
    return this.http.get(
      'http://localhost:8080/api/user/changePercentage/' + newPercentage
    );
  };

  getAllUsers = () => {
    return this.http.get('http://localhost:8080/api/user/getAllUsers').pipe(
      map((responseData: any) => {
        return responseData;
      })
    );
  };

  getAllEntities = () => {
    return this.http.get('http://localhost:8080/api/reservation-entity').pipe(
      map((responseData: any) => {
        return responseData;
      })
    );
  };

  getAllDeleteRequests = () => {
    return this.http.get('http://localhost:8080/api/user/deleteRequests').pipe(
      map((responseData: any) => {
        return responseData;
      })
    );
  };

  acceptDeleteRequest = (dto: DeleteRequestModel) => {
    return this.http.post(
      'http://localhost:8080/api/user/acceptDeleteRequest',
      dto
    );
  };

  declineDeleteRequest = (dto: DeleteRequestModel) => {
    return this.http.post(
      'http://localhost:8080/api/user/declineDeleteRequest',
      dto
    );
  };

  deleteUser = (id: string) => {
    return this.http.get('http://localhost:8080/api/user/deleteUser/' + id);
  };

  deleteEntity = (id: string) => {
    return this.http.get(
      'http://localhost:8080/api/reservation-entity/deleteEntity/' + id
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

  addAdmin = (model: RegistrationModel) => {
    return this.http.post('http://localhost:8080/api/user/addAdmin', model);
  };
}
