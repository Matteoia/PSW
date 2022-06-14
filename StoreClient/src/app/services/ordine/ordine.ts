import { ClassField } from "@angular/compiler";
import { cliente } from "../cliente/cliente";
import { lineaOrdine } from "../lineaOrdine/lineaOrdine";

export class ordine{
    id: number = 0;
    cliente: cliente | null = null;
    data: Date = new Date();
    lineeOrdini: lineaOrdine[] = [];

    constructor(c: cliente, d: Date, lo: lineaOrdine[]){
        this.cliente = c;
        this.data = d;
        this.lineeOrdini = lo;
    }
}