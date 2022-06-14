import { Component, NgModule, OnInit, ViewChild } from '@angular/core';
import { prodotto } from '../services/prodotto/prodotto';
import { StoreComponent } from '../store/store.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  mostraC: boolean = false;
  @ViewChild(StoreComponent)
  private child!: StoreComponent;
  prodottiCarrello: prodotto[] = [];
  
  constructor() {}
  ngOnInit(): void {
  }

  mostraCarrello(mostra: boolean){
    this.mostraC = mostra;
    this.child.mostraCarrello();
  }

  addToCart(prodotti: prodotto[]){
      this.prodottiCarrello = prodotti;
  }
}
