import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { prodotto } from '../services/prodotto/prodotto';
import { ProdottoService } from '../services/prodotto/prodotto.service';

@Component({
  selector: 'app-store',
  templateUrl: './store.component.html',
  styleUrls: ['./store.component.css'],
})
export class StoreComponent implements OnInit {
  prodotti: prodotto[] = [];
  mostraProdotti: boolean = false;
  prodottiCarrello: prodotto[] = [];
  @Output() mostraC = new EventEmitter<prodotto[]>();

  constructor( private prodottoService: ProdottoService) { }

  public ngOnInit(){
    this.getProdotti();
  }

  public getProdotti(){
    this.prodottoService.getProdotti().subscribe(
      (response: prodotto[]) => {
        this.prodotti = response;
        this.mostraProdotti = true;
      }
    );
  }

  public addToCart(prodotto: prodotto){
    console.log(this.prodottiCarrello);
    this.prodottiCarrello.push(prodotto);
  }

  public mostraCarrello(){
    console.log("Funzione chiamata dal parent")
    this.mostraC.emit(this.prodottiCarrello);
  }
}
