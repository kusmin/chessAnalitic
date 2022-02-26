import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Credenciais } from '../dto/credenciais';
import { Autorizacao } from '../dto/autorizacao';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private apiHost: string = environment.apiHost;

  private ouvintes:Array<Function> = []

  public subscribe(fn:Function) {
    this.ouvintes.push(fn)
  }

  private informar(evento:string) {
    if (this.ouvintes && this.ouvintes.length > 0) {
      this.ouvintes.forEach((ouvinte:Function) => {
        ouvinte(evento)
      })
    }
  }

  public autorizacao():Autorizacao {
    let result:Autorizacao = null
    let dados:string = localStorage.getItem('autorizacao-admin')
    if (dados != null) {
      result = JSON.parse(dados)
    }
    return result;
  }

  public autenticar(credenciais:Credenciais, callback:Function) {
    this.http.post(this.apiHost + '/api/v1/auth', credenciais).subscribe(
      (sucesso:Autorizacao) => {
        if (!sucesso.roles.includes('ROLE_ADMIN')) {
          {callback({}, null); }
          return;
        }
        localStorage.setItem('autorizacao-admin', JSON.stringify(sucesso));
        this.informar('loggedIn')
        callback(null, sucesso)
      },
      (falha:any) => {callback(falha, null)}
    )
  }

  public authorizationHeader(){
    var httpHeaders: HttpHeaders = new HttpHeaders({
      Authorization: this.autorizacao().token
    });

    return httpHeaders;
  }

  public logout(callback:Function) {
    localStorage.clear()
    this.informar('loggedOut')
    callback()
  }

  constructor(
    private http:HttpClient
  ) { }
}
