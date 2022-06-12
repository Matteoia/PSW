export class cliente {
    id: number = 0;
    nome: string | undefined = "";
    cognome: string | undefined= "";
    email: string | undefined = "";
    
    constructor() {}

    setNome(nome: string| undefined){
        this.nome = nome;
    }
    setCognome(cognome: string | undefined){
        this.cognome = cognome;
    }

    setEmail(email: string | undefined){
        this.email = email;
    }
}