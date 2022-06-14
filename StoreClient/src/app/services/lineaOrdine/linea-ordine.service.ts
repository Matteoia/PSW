import { lineaOrdine } from './lineaOrdine';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class LineaOrdineService {
  private apiServerUrl = environment.apiBaseUrl; 
  
  constructor(private http: HttpClient) { }

  getLineeOrdine(ordineId: number): Observable<lineaOrdine[]>{
    return this.http.get<lineaOrdine[]>(this.apiServerUrl+"/ordine/lineeOrdine/"+ordineId);
  }
}
