import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { ordine } from './ordine';

@Injectable({
  providedIn: 'root'
})
export class OrdineService {
  private apiServerUrl = environment.apiBaseUrl; 
  
  constructor(private http: HttpClient) { }

  crea(o: ordine){
    console.log("ordino "+o);
    this.http.post<ordine>(this.apiServerUrl+"/ordine", o).subscribe();
  }
}
