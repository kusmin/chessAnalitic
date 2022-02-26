import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpErrorResponse
} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { Router } from '@angular/router';
import { LoginService } from '../services/login.service';
import { catchError } from 'rxjs/operators';
import { NgxSpinnerService } from 'ngx-spinner';

@Injectable()
export class ResponseInterceptor implements HttpInterceptor {

  constructor(
    private router: Router,
    private loginService: LoginService,
    private spinner: NgxSpinnerService
  ) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(request).pipe(
      catchError((error: HttpErrorResponse) => {
        let loginFalho: boolean = false
        loginFalho = request.url.endsWith('auth')
        this.spinner.hide();
        if(! loginFalho && (error.status == 401 || error.status == 403)){
          this.loginService.logout(() => {
            this.router.navigateByUrl('')
          })
        }
        return throwError(error);
      })
    )
  }
}
