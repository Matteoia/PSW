import { KeycloakService } from 'keycloak-angular';
import { KeycloakProfile, KeycloakLoginOptions } from 'keycloak-js';
import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-barra',
  templateUrl: './barra.component.html',
  styleUrls: ['./barra.component.css']
})
export class BarraComponent {
  @Output() mostraC = new EventEmitter<boolean>();
  showHome: boolean = false;
  public isLoggedIn = false;
  public userProfile: KeycloakProfile | null = null;
  
  public constructor(public keycloak: KeycloakService) {}
  
  public async ngOnInit() {
    this.isLoggedIn = await this.keycloak.isLoggedIn();
    if (this.isLoggedIn) {
      this.userProfile = await this.keycloak.loadUserProfile();
    }
  }

  public login() {
    this.keycloak.login();
  }

  public logout() {
    this.keycloak.logout();
  }

  public register(){
    const redirect: KeycloakLoginOptions = {
      redirectUri: 'http://localhost:4200/reg-ok'
    }
    this.keycloak.register(redirect);
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
