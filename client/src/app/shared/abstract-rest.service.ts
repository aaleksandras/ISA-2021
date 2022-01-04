import {Injectable} from '@angular/core';
import {HttpClient, HttpParams, HttpResponse} from "@angular/common/http";
import {Observable} from "rxjs";
import {DefaultModel} from "./default-model";

export class AbstractRestService<T extends DefaultModel> {

  private baseUrl: string = 'http://localhost:8080'

  constructor(public http: HttpClient, protected resourceUrl: string) { }


  create(t: T): Observable<HttpResponse<T>> {
    return this.http.post<T>(this.baseUrl + this.resourceUrl, t, { observe: 'response' });
  }

  update(id: number, t: T): Observable<HttpResponse<T>> {
    return this.http.put<T>(`${this.baseUrl}${this.resourceUrl}/${id}`, t, { observe: 'response' });
  }

  find(id: number): Observable<HttpResponse<T>> {
    return this.http.get<T>(`${this.baseUrl}${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(params?: HttpParams): Observable<HttpResponse<T[]>> {
    if (params == null) {
      params = this.getDefaultParams();
    }
    return this.http.get<T[]>(`${this.baseUrl}${this.resourceUrl}`, { observe: 'response', params });
  }

  queryAll(params?: HttpParams): Observable<HttpResponse<T[]>> {
    return this.http.get<T[]>(`${this.baseUrl}${this.resourceUrl}/all`, { observe: 'response', params });
  }

  getDefaultParams(): HttpParams {
    return new HttpParams()
      .set('page', '0')
      .set('size', '10')
      .set('sort', 'id,asc');
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.baseUrl}${this.resourceUrl}/${id}`, { observe: 'response' });
  }

}
