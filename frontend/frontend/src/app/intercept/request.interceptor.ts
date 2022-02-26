import { Injectable, NgModule } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';

import { HTTP_INTERCEPTORS } from '@angular/common/http';

import { LoginService } from '../services/login.service';

@Injectable()
export class RequestInterceptor implements HttpInterceptor {

  constructor(
    private autorizacaoService: LoginService
) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    if (this.autorizacaoService.autorizacao() && this.autorizacaoService.autorizacao().token) {
        const requestComCabecalho = req.clone({
            headers: req.headers
                .set('Authorization', this.autorizacaoService.autorizacao().token)
            });

        return next.handle(requestComCabecalho);
    }
    return next.handle(req);
  }
}

@NgModule({
  providers: [
      {
          provide: HTTP_INTERCEPTORS,
          useClass: RequestInterceptor,
          multi: true,
      },
  ],
})

export class InterceptorModule { }