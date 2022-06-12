import { IdentityManagerService } from './../../utility/identity-manager.service';
import { KeycloakProfile } from 'keycloak-js';
import { Injectable } from '@angular/core';
import { cliente } from './cliente';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})

export class ClienteService {
  private apiServerUrl = environment.apiBaseUrl;
  private cliente: cliente = new cliente();
 
  constructor(private http: HttpClient, identity: IdentityManagerService) { }

  crea(userProfile: KeycloakProfile){
    console.log(userProfile);
    this.cliente.setNome("");
    this.cliente.setCognome(userProfile.lastName);
    this.cliente.setEmail(userProfile.email);
    console.log("invio l'utente "+cliente);
    this.http.post<any>(this.apiServerUrl+"/cliente", this.cliente);
  }
}
