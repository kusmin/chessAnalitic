import { Component } from '@angular/core';
import { environment } from '../environments/environment';
import { LoginService } from './services/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  public version: string = environment.version;

  public menuAtivo: boolean = false;

  public classeAbaCampanha: string;
  public classeAbaEngajamento: string;
  public classeAbaAdesao: string;
  public classeAbaFeedbacks: string;

  public showModal: boolean;
  public showModalLogout: boolean;

  title = 'Weex Dashboard';

  public showHideModal(){
    this.showModal = ! this.showModal;
  }

  public showHideModalLogout(){
    this.showModalLogout = ! this.showModalLogout;
  }

  public exporMenu() {
    this.menuAtivo = ! this.menuAtivo;
  }

  public goHome(): void {
    if (this.loginService.autorizacao() != null) {
      this.router.navigateByUrl('/home')
    } else {
      this.router.navigateByUrl('/')
    }
  }

  public statusLogin: string = "loggedOut";

  public logout():void {
    this. showModalLogout = false;
    this.loginService.logout(() => {
      this.router.navigateByUrl('')
    })
  }

  public autenticado():boolean {
    return this.loginService.autorizacao() != null
  }

  private listenLogin(evento:string) {
    this.statusLogin = evento;
  }

  alterarAba(aba: string) {
    if (aba === 'campanha') {
      this.classeAbaCampanha = 'is-active';
      this.classeAbaEngajamento = '';
      this.classeAbaAdesao = '';
      this.classeAbaFeedbacks = '';
    } else if (aba === 'engajamento') {
      this.classeAbaCampanha = '';
      this.classeAbaEngajamento = 'is-active';
      this.classeAbaAdesao = '';
      this.classeAbaFeedbacks = '';
    } else if (aba == 'adesao'){
      this.classeAbaCampanha = '';
      this.classeAbaEngajamento = '';
      this.classeAbaAdesao = 'is-active';
      this.classeAbaFeedbacks = '';
    } else if(aba == 'feedbacks'){
      this.classeAbaCampanha = '';
      this.classeAbaEngajamento = '';
      this.classeAbaAdesao = '';
      this.classeAbaFeedbacks = 'is-active';
    }
    
  }

  constructor(
  
    private loginService:LoginService,
    private router:Router
    ) {}

  public ngOnInit() {
    this.showModal = false;
    this.showModalLogout = false;
    if (this.loginService.autorizacao() != null) {
      this.statusLogin = "loggedIn";
    } else {
      this.statusLogin = "loggedOut";
      this.router.navigate(['/']);
    }
    this.loginService.subscribe((eventoLogin:string) => {
      this.statusLogin = eventoLogin;
    })

    this.classeAbaCampanha = '';
    this.classeAbaEngajamento = '';
    this.classeAbaAdesao = '';
    this.classeAbaFeedbacks = '';

  }

}
