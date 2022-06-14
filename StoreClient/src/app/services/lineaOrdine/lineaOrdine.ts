import { prodotto } from './../prodotto/prodotto';
import { ordine } from './../ordine/ordine';
export class lineaOrdine{
    id: number = 0; 
    ordine: ordine | null = null;
    prodotto: prodotto;
    qta: number;
    prezzo: number;

    constructor(p: prodotto, qta: number, prezzo: number){
        this.prodotto = p;
        this.qta = qta;
        this.prezzo = prezzo;
    }
}