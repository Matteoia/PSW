import { AppModule } from '../../app.module';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { prodotto } from './prodotto';

@Injectable()

export class ProdottoService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getProdotti(pn: number): Observable<prodotto[]> {
    let queryParams = new HttpParams();
    queryParams = queryParams.append("pageNumber",pn);
    return this.http.get<prodotto[]>(this.apiServerUrl+"/prodotto/paged", {params: queryParams});
  }

  
}