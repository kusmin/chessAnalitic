import { TestBed } from '@angular/core/testing';

import { LoginService } from './login.service';
import { HttpClientModule } from '@angular/common/http';
import { Credenciais } from '../dto/credenciais';
import { Autorizacao } from '../dto/autorizacao';

describe('LoginService', () => {
  beforeEach(() => TestBed.configureTestingModule({
    imports:[HttpClientModule],
    providers:[LoginService]
  }));

  it('devo poder obter o serviço', () => {
    const service: LoginService = TestBed.get(LoginService);
    expect(service).toBeTruthy();
  });

  it('uma autenticação bem sucedida', (done:DoneFn) => {
    const service: LoginService = TestBed.get(LoginService);

    let credenciais:Credenciais = new Credenciais();
    credenciais.username = 'admin@itexto.com.br';
    credenciais.password = 'admin1234';

    service.autenticar(credenciais, (erro:any, sucesso:Autorizacao) => {
      
      expect(erro).toBeFalsy('Não deveria ter me retornado um erro');
      expect(sucesso).toBeTruthy()
      expect(sucesso.username).toEqual(credenciais.username, 'Retornou um e-mail diferente')
      expect(sucesso.token).toBeTruthy('Não me retornou um token de autorização')

      let autorizacao:string = localStorage.getItem('autorizacao')
      expect(autorizacao).toBeTruthy('A autorização deveria ter sido persistida')
      
      let auth:Autorizacao = JSON.parse(autorizacao)
      expect(auth.token).toEqual(sucesso.token, 'Um token diferente foi persistido')

      done()

    })

  })

  it('quando ocorrer o logout, eu devo ter o meu localstorage limpo', (done:DoneFn) => {
    let service:LoginService = TestBed.get(LoginService);
    let credenciais:Credenciais = new Credenciais();
    credenciais.username = 'admin@itexto.com.br';
    credenciais.password = 'admin1234';

    service.autenticar(credenciais, (erro:any, sucesso:Autorizacao) => {

      expect(erro).toBeFalsy('Deveria ter falhado')
      expect(credenciais).toBeTruthy('Deveria ter ocorrido com sucesso a autenticação')

      service.logout(() => {
        expect(localStorage.getItem('autorizacao')).toBeFalsy('Não poderia estar a autorização mais no local storage')
        expect(localStorage.length).toBe(0, 'O local storage deveria estar limpo')
        done()
      })

    })

  })

  it('uma autenticação mal sucedida', (done:DoneFn) => {

    let credenciais:Credenciais = new Credenciais()
    credenciais.username = "asdfasdfaswdf";
    credenciais.password = "asdfasdfasdf"

    let service:LoginService = TestBed.get(LoginService);

    service.autenticar(credenciais, (erro:any, sucesso:Autorizacao) => {
      expect(erro).toBeTruthy('Um erro deveria ter sido retornado. Foram enviadas credenciais inválidas')
      expect(sucesso).toBeFalsy('Não poderia retornar algo, dado que as credenciais fornecidas foram inválidas')
      done()
    })

  })

  it('após uma autenticação bem sucedida, eu devo sempre poder ter acesso aos dados das minhas autorizações', (done:DoneFn) => {
    let service:LoginService = TestBed.get(LoginService);
    let credenciais:Credenciais = new Credenciais();
    credenciais.username = 'admin@itexto.com.br';
    credenciais.password = 'admin1234';
    service.autenticar(credenciais, (erro:any, sucesso:Autorizacao) => {
      expect(erro).toBeFalsy('Autenticação deveria ter sido bem sucedida')
      expect(sucesso).toBeTruthy('Autenticação deveria ter sido bem sucedida')

      expect(service.autorizacao()).toBeTruthy('Deveria ter acesso à autorização')
      expect(service.autorizacao().token).toEqual(sucesso.token, "Está me retornando um token inesperado")
      done()
    })
  })

});
