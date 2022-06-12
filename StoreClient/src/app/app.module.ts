import { IdentityManagerService } from './utility/identity-manager.service';
import { KeycloakService } from 'keycloak-angular';
import { ClienteService } from './services/cliente/cliente.service';
import { OrdineService } from './services/ordine/ordine.service';
import { ProdottoService } from './services/prodotto/prodotto.service';

import { AuthModule } from './utility/auth.module';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatToolbarModule } from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';

import { CarrelloComponent } from './carrello/carrello.component';
import { BarraComponent } from './barra/barra.component';
import { StoreComponent } from './store/store.component';
import { RegOKComponent } from './reg-ok/reg-ok.component';
import { HomeComponent } from './home/home.component';


@NgModule({
  declarations: [AppComponent, StoreComponent, BarraComponent, CarrelloComponent, RegOKComponent, HomeComponent],
  imports: [
    AppRoutingModule,
    BrowserModule,
    AuthModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    HttpClientModule,
  ],
  providers: [ ProdottoService, OrdineService, ClienteService, KeycloakService],
  bootstrap: [AppComponent]
})
export class AppModule {}
