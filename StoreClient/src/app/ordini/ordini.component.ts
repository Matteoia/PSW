import { LineaOrdineService } from './../services/lineaOrdine/linea-ordine.service';
import { lineaOrdine } from './../services/lineaOrdine/lineaOrdine';
import { KeycloakService } from 'keycloak-angular';
import { OrdineService } from './../services/ordine/ordine.service';
import { Component, OnInit } from '@angular/core';
import { ordine } from '../services/ordine/ordine';
import { KeycloakProfile } from 'keycloak-js';

@Component({
  selector: 'app-ordini',
  templateUrl: './ordini.component.html',
  styleUrls: ['./ordini.component.css']
})
export class OrdiniComponent implements OnInit {
  public ordiniEffettuati: ordine[] = [];
  public lineeOrdini: lineaOrdine[][] = [];
  public isLoggedIn = false;
  public userProfile: KeycloakProfile | null = null;
  
  constructor(private ordineService: OrdineService, private keycloak: KeycloakService, private loService: LineaOrdineService) { }

  public async ngOnInit() {
    this.isLoggedIn = await this.keycloak.isLoggedIn();
    if (this.isLoggedIn) {
      this.userProfile = await this.keycloak.loadUserProfile();
    }

    this.ordineService.getByEmail(this.userProfile?.email as string).subscribe((result: ordine[]) =>{
      result.forEach((o: ordine) => {
        this.ordiniEffettuati.push(o);
        this.loService.getLineeOrdine(o.id).subscribe((resLO: lineaOrdine[]) => {
          this.lineeOrdini.push(resLO);
        });
      });
    });
  }
}
