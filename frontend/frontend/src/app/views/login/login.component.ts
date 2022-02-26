import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';
import { Credenciais } from 'src/app/dto/credenciais';
import { Autorizacao } from 'src/app/dto/autorizacao';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public credenciais:Credenciais = new Credenciais()

  public mensagemErro:string = null

  public entrar():void {
    this.loginService.autenticar(this.credenciais, (erro:any, sucesso:Autorizacao) => {
      if (erro) {
        this.mensagemErro = "E-mail ou senha inválidos"
      } else if (sucesso) {
        this.mensagemErro = null
        this.router.navigateByUrl('home')
      }
    })
  }

  constructor(
    private loginService:LoginService,
    private router:Router
  ) { }

  ngOnInit(): void {
  }

}
