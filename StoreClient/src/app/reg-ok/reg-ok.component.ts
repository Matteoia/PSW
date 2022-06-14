import { KeycloakService } from 'keycloak-angular';
import { KeycloakProfile } from 'keycloak-js';
import { Component, OnInit } from '@angular/core';
import { ClienteService } from '../services/cliente/cliente.service';


@Component({
  selector: 'app-reg-ok',
  templateUrl: './reg-ok.component.html',
  styleUrls: ['./reg-ok.component.css']
})
export class RegOKComponent implements OnInit {
  public isLoggedIn = false;
  public userProfile: KeycloakProfile | null = null;
  
  public constructor(private cliente: ClienteService, public keycloak: KeycloakService) {
  }
  
  public async ngOnInit() {
    this.isLoggedIn = await this.keycloak.isLoggedIn();
    if (this.isLoggedIn) {
      this.userProfile = await this.keycloak.loadUserProfile();
    }
    this.cliente.crea(this.keycloak);  
  }

}
