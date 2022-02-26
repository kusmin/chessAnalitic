import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { LOCALE_ID } from "@angular/core";
import localePt from "@angular/common/locales/pt";
import { registerLocaleData } from "@angular/common";
import { HomeComponent } from './views/home/home.component';
import { LoginComponent } from './views/login/login.component';
import { ResponseInterceptor } from "./intercept/response.interceptor";
import { RequestInterceptor } from "./intercept/request.interceptor";
import { NgxSpinnerModule } from "ngx-spinner";
import { NgxNumberFormatModule } from "ngx-number-format";
import { ToastrModule } from "ngx-toastr";
import { CampanhaComponent } from './views/campanha/campanha.component';
import { EngajamentoComponent } from './views/engajamento/engajamento.component';
import { AdesaoComponent } from './views/adesao/adesao.component';
import { FeedbacksComponent } from './views/feedbacks/feedbacks.component';

registerLocaleData(localePt);

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    CampanhaComponent,
    EngajamentoComponent,
    AdesaoComponent,
    FeedbacksComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    NgxSpinnerModule,
    NgxNumberFormatModule,
    ToastrModule.forRoot({
      timeOut: 5000,
      positionClass: "toast-top-right",
      preventDuplicates: false,
    }),
  ],
  providers: [
    { provide: LOCALE_ID, useValue: "pt" },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: ResponseInterceptor,
      multi: true,
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: RequestInterceptor,
      multi: true,
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
