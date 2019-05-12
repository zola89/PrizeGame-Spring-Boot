import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/internal/Observable';
import {Prize} from "./prize.model";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable()
export class PrizeService {

  private url = 'Prizes';  // URL to web api

  constructor(private http: HttpClient) {
  }

  public getPrizes(): Observable<Prize[]> {
    return this.http.get<Prize[]>(this.url);
  }

  deleteById(id: any) {
    return this.http.delete(this.url + '/' + id);
  }

  getById(id: any): Observable<Prize> {
    return this.http.get<Prize>(this.url + '/' + id);
  }

  save(Prize: Prize): Observable<Prize> {
    return this.http.post<Prize>(this.url, Prize);
  }

  update(id: any, Prize: Prize): Observable<Prize> {
    return this.http.put<Prize>(this.url + '/' + id, Prize);
  }

}
