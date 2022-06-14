import { KeycloakProfile } from 'keycloak-js';
export class cliente {
    id: number = 0;
    nome: string | undefined = "";
    cognome: string | undefined= "";
    email: string | undefined = "";
    
    constructor(keycloak: KeycloakProfile) {
        this.nome = keycloak.firstName;
        this.cognome = keycloak.lastName;
        this.email = keycloak.email;
    }
}