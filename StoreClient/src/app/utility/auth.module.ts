import { KeycloakAngularModule, KeycloakService } from 'keycloak-angular';
import { APP_INITIALIZER } from "@angular/core";
import { NgModule } from '@angular/core';

function initializeKeycloak(keycloak: KeycloakService) {
  return () =>
  keycloak.init({
    shouldAddToken: (request) => {
      const { method, url } = request;
      console.log(method.toUpperCase());
      const isGetRequest = 'GET' === method.toUpperCase();

      return !(isGetRequest);
    },
    config: {
      url: 'http://localhost:8080',
      realm: 'StoreProject',
      clientId: 'store-client'
    },
    initOptions: {
      onLoad: 'check-sso',
      checkLoginIframe: false
    },
  });
}

@NgModule({
  declarations:[],
  imports: [KeycloakAngularModule],
  providers: [
    {
      provide: APP_INITIALIZER,
      useFactory: initializeKeycloak,
      multi: true,
      deps: [KeycloakService]
    }
  ],
})

export class AuthModule{}
