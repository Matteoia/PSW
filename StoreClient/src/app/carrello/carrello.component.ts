import { LineaOrdineService } from './../services/lineaOrdine/linea-ordine.service';
import { lineaOrdine } from './../services/lineaOrdine/lineaOrdine';
import { OrdineService } from './../services/ordine/ordine.service';
import { ordine } from './../services/ordine/ordine';
import { Component, Input, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { KeycloakService } from 'keycloak-angular';
import { KeycloakProfile } from 'keycloak-js';
import { prodotto } from '../services/prodotto/prodotto';
import { cliente } from '../services/cliente/cliente';

@Component({
  selector: 'app-carrello',
  templateUrl: './carrello.component.html',
  styleUrls: ['./carrello.component.css']
})
export class CarrelloComponent implements OnInit{
  @Input()
  prodotti: prodotto[] = [];
  qta: [number, number][] = [];
  index: number = 0;
  totale: number;
  isLoggedIn = false;
  userProfile: KeycloakProfile | null = null;
  
  public constructor(public keycloak: KeycloakService, private snackBar: MatSnackBar, private ordineService: OrdineService) {
    this.totale = 0;
  }
  
  async ngOnInit() {
    this.isLoggedIn = await this.keycloak.isLoggedIn();
    if (this.isLoggedIn) {
      this.userProfile = await this.keycloak.loadUserProfile();
    }

    this.prodotti.forEach((value: prodotto) =>{
      this.totale += value.prezzo;
      const temp: [number, number] = [value.id, 1]; 
      this.qta.push(temp);
    });
  }

  ordina(){
    if(!this.isLoggedIn){
      this.snackBar.open("Bisogna essere loggati per ordinare", "OK");
    }else{
      var c: cliente = new cliente(this.userProfile as KeycloakProfile);
      var d: Date = new Date();
      var lo: lineaOrdine[] = [];
      this.prodotti.forEach((p : prodotto) => {
        lo.push(new lineaOrdine(p, this.qta[this.prodotti.indexOf(p)][1], p.prezzo));
      });
      var o: ordine = new ordine(c, d, lo);
      console.log(o);
      this.ordineService.crea(o);
    }
    
  }

  addQta(prodotto: prodotto){
    this.qta.forEach((value: [number, number]) => {
      if(value[0] == prodotto.id){
        value[1]++;
        this.totale += prodotto.prezzo;
      }
    });
  }

  remQta(prodotto: prodotto){
    this.qta.forEach((value: [number, number]) => {
      if(value[0] == prodotto.id){
        value[1]--;
        this.totale -= prodotto.prezzo;
        if(value[1] == 0){
          this.rimuovi(prodotto, true);
        }
      }
    });
  }

  rimuovi(prodotto: prodotto, ultimo: boolean){
    this.prodotti.forEach((value: prodotto, index: number)=>{
      if(value.id==prodotto.id) {
        this.prodotti.splice(index,1);
        if(!ultimo)
          this.totale -= prodotto.prezzo;
      }
  });
  }

}
