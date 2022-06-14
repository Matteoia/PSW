import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ordine } from './ordine';

@Injectable({
  providedIn: 'root'
})
export class OrdineService {
  private apiServerUrl = environment.apiBaseUrl; 
  
  constructor(private http: HttpClient) { }

  crea(o: ordine){
    this.http.post<ordine>(this.apiServerUrl+"/ordine", o).subscribe();
  }

  getByEmail(email: string): Observable<ordine[]>{
    return this.http.get<ordine[]>(this.apiServerUrl+"/ordine/getByEmail/"+email);
  }
}
