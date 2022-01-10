import { HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { throwError } from 'rxjs';
import { SignInModel } from '../model/SignInModel';
import { ApiService } from './api.service';
import { ConfigService } from './config.service';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  headers = new HttpHeaders().set('Content-Type', 'application/json');
  currentUser = {};
  constructor(
    private apiService: ApiService,
    private config: ConfigService,
    private router: Router
  ) { }

  private access_token = null;

  signIn(user: SignInModel) {
    return this.apiService
      .post('http://localhost:8080/api/auth', user)
      .subscribe(
        (res: any) => {
          if (!res.isActive) {
            alert("Your account is not verified yet!");
            return;
          }
          if (res.isFirstLogin) {
            localStorage.setItem('firstLogin', "true");
            window.location.href = "/changePassword";
          }
          else {
            localStorage.setItem('firstLogin', "false");
          }
          localStorage.setItem('token', res.token);
          this.currentUser = res;
          localStorage.setItem('role', res.role);
          localStorage.setItem('user_id', res.id);
          this.router.navigate(['']);
        },
        (error) => {
          if (error.status === 401)
            alert('Wrong email or password!');
        }
      );
  }

  getToken() {
    return localStorage.getItem('token');
  }

  getIsFirstLogin() {
    return localStorage.getItem('firstLogin');
  }

  getRole() {
    return localStorage.getItem('role');
  }

  getId() {
    return localStorage.getItem('user_id');
  }
  get isLoggedIn(): boolean {
    let authToken = localStorage.getItem('token');
    return authToken !== null ? true : false;
  }

  doLogout() {
    localStorage.removeItem('token');
    localStorage.removeItem('role');
    localStorage.removeItem('user_id');
  }

  tokenIsPresent() {
    return this.access_token != undefined && this.access_token != null;
  }

  handleError(error: HttpErrorResponse) {
    let msg = '';
    if (error.error instanceof ErrorEvent) {
      // client-side error
      msg = error.error.message;
    } else {
      // server-side error
      msg = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    return throwError(msg);
  }
}
