import { cliente } from "../cliente/cliente";

export interface ordine{
    id: number;
    cliente: cliente;
    data: Date;
}