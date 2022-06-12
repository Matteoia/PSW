import { Injectable } from '@angular/core';
import { KeycloakService } from 'keycloak-angular';
import { KeycloakLoginOptions, KeycloakProfile } from 'keycloak-js';

@Injectable({
  providedIn: 'root'
})

export class IdentityManagerService {
  private static instance: IdentityManagerService;
  public isLoggedIn = false;
  public userProfile: KeycloakProfile | null = null;


  private constructor(private readonly keycloak: KeycloakService) {}

  public static getInstance(): IdentityManagerService {
    if(!IdentityManagerService.instance){
      IdentityManagerService.instance = new IdentityManagerService(new KeycloakService());
    }
    return IdentityManagerService.instance;
  }
  

  public async ngOnInit() {
    this.isLoggedIn = await this.keycloak.isLoggedIn();
    if (this.isLoggedIn) {
      this.userProfile = await this.keycloak.loadUserProfile();
    }
  }

  public login(redirect: KeycloakLoginOptions) {
    this.keycloak.login(redirect);
  }

  public logout() {
    this.keycloak.logout();
  }

    public register(redirect: KeycloakLoginOptions){
      this.keycloak.register(redirect);
    }

    public getToken(){
      token: String;  
      const token = this.keycloak.getToken();
      console.log("Ecco il token :)");
      console.log(token);
    }
}
