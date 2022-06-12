import { prodotto } from './../prodotto/prodotto';
import { ordine } from './../ordine/ordine';
export interface lineaOrdine{
    id: number;
    ordine: ordine;
    prodotto: prodotto;
    qta: number;
    prezzo: number;
}