import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/internal/Observable';
import {Code} from './code.model';


const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable()
export class CodeService {

  private url = 'codes';

  constructor(private http: HttpClient) {
  }

  public getCodes(): Observable<Code[]> {
    return this.http.get<Code[]>(this.url);
  }

  deleteById(id: any) {
    return this.http.delete(this.url + '/' + id);
  }

  getById(id: any): Observable<Code> {
    return this.http.get<Code>(this.url + '/' + id);
  }

  save(code: Code): Observable<Code> {
    return this.http.post<Code>(this.url, code);
  }

  update(id: any, code: Code): Observable<Code> {
    return this.http.put<Code>(this.url + '/' + id, code);
  }

  getByPrizeCode(prizeCode: any): Observable<Code> {
    return this.http.get<Code>(this.url + '/prize/' + prizeCode);
  }

  getByUserId(id: any): Observable<Code[]> {
    return this.http.get<Code[]>(this.url + '/user/' + id);
  }

}
