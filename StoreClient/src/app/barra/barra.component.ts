import { KeycloakLoginOptions } from 'keycloak-js';
import { IdentityManagerService } from '../utility/identity-manager.service';
import { ClienteService } from '../services/cliente/cliente.service';
import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-barra',
  templateUrl: './barra.component.html',
  styleUrls: ['./barra.component.css']
})
export class BarraComponent {
  @Output() mostraC = new EventEmitter<boolean>();
  showHome: boolean = false;
  identity: IdentityManagerService;

  constructor(private clienteService: ClienteService) {
    this.identity = IdentityManagerService.getInstance();
  }

  public ngOnInit() {
   // this.identity.ngOnInit();
  }

  public login() {
    const redirect: KeycloakLoginOptions = {redirectUri: 'http://localhost:4200/reg-ok'};
    this.identity.login(redirect);
  }

  public logout() {
    this.identity.logout();
  }

  public register(){
    const redirect: KeycloakLoginOptions = {redirectUri: 'http://localhost:4200/reg-ok'};
    this.identity.register(redirect);
  }

  public mostraCarrello(){
    this.mostraC.emit(true);
    this.showHome = true;
  }

  public mostraHome(){
    this.mostraC.emit(false);
    this.showHome = false;
  }
}
