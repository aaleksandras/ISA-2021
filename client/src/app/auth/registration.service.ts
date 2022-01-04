import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { RegistrationModel } from '../model/RegistrationModel';
import { AbstractRestService } from '../shared/abstract-rest.service';

@Injectable({
  providedIn: 'root',
})
export class RegistrationService extends AbstractRestService<RegistrationModel> {
  constructor(public http: HttpClient) {
    super(http, '/api/user');
  }
}
