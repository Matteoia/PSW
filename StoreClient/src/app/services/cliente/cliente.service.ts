import { Observable } from 'rxjs';
import { KeycloakProfile } from 'keycloak-js';
import { Injectable } from '@angular/core';
import { cliente } from './cliente';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { KeycloakService } from 'keycloak-angular';

@Injectable({
  providedIn: 'root'
})

export class ClienteService {
  private apiServerUrl = environment.apiBaseUrl; 

  constructor(private http: HttpClient) { }

  async crea(keycloak: KeycloakService){
    const userProfile: KeycloakProfile = await keycloak.loadUserProfile();
    var c : cliente = new cliente(userProfile);
    this.http.post<cliente>(this.apiServerUrl+"/cliente", c).subscribe();
  }

}
