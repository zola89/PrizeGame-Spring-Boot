import {Injectable} from '@angular/core';
import {
  HttpErrorResponse,
  HttpHandler,
  HttpHeaderResponse,
  HttpInterceptor,
  HttpProgressEvent,
  HttpRequest,
  HttpResponse,
  HttpSentEvent,
  HttpUserEvent
} from '@angular/common/http';
import {Router} from '@angular/router';
import 'rxjs/add/operator/do';
import {Observable} from 'rxjs';
import {TokenStorage} from './auth/token.storage';
import {environment} from '../../environments/environment';
import {AlertService} from './alert/alert-service';

const TOKEN_HEADER_KEY = 'Authorization';

@Injectable()
export class Interceptor implements HttpInterceptor {

  constructor(private token: TokenStorage, private router: Router, public alertService: AlertService) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler):

    Observable<HttpSentEvent | HttpHeaderResponse | HttpProgressEvent | HttpResponse<any> | HttpUserEvent<any>> {

    this.alertService.showSpinner();

    let authReq = req;

    if (this.token.getToken() != null) {
      authReq = req.clone({
        url: environment.baseUrl + req.url,
        headers: req.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + this.token.getToken())
      });
    } else {
      authReq = req.clone({
        url: environment.baseUrl + req.url
      });
    }

    return next.handle(authReq).do(
      (response) => {
        if (response instanceof HttpResponse) {
          this.alertService.processResponse(response);
        }
      },
      (error) => {
        if (error instanceof HttpErrorResponse) {
          this.alertService.processResponse(error);
        }
      },
      () => {
        this.alertService.hideSpinner();
      }
    );

  }

}
