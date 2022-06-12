import { Component, Input } from '@angular/core';
import { prodotto } from '../services/prodotto/prodotto';

@Component({
  selector: 'app-carrello',
  templateUrl: './carrello.component.html',
  styleUrls: ['./carrello.component.css']
})
export class CarrelloComponent {
  @Input()
  prodotti: prodotto[] = [];
  index: number = 0;
  totale: number;

  constructor() {
    this.totale = 0;
  }

  ordina(){
    
  }

  rimuovi(prodotto: prodotto){
    this.prodotti.forEach((value: prodotto, index: number)=>{
      if(value.id==prodotto.id) this.prodotti.splice(index,1);
  });
  }

}
