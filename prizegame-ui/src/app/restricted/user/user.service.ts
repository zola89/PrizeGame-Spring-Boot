import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/internal/Observable';
import {User} from './user.model';


const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable()
export class UserService {

  private url = 'user';  // URL to web api

  constructor(private http: HttpClient) {
  }

  public getUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.url);
  }

  deleteById(id: any) {
    return this.http.delete(this.url + '/' + id);
  }

  getById(id: any): Observable<User> {
    return this.http.get<User>(this.url + '/' + id);
  }

  save(user: User): Observable<User> {
    return this.http.post<User>(this.url, user);
  }

  update(id: any, user: User): Observable<User> {
    return this.http.put<User>(this.url + '/' + id, user);
  }

}
