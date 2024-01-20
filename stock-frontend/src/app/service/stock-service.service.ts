import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Stock } from '../model/stock';
// @ts-ignore
import { Observable } from 'rxjs/Observable';

@Injectable()
export class StockService {

  private stocksUrl: string;

  constructor(private http: HttpClient) {
    this.stocksUrl = 'http://localhost:52001/stocks';
  }

  public findAll(): Observable<Stock[]> {
    return this.http.get<Stock[]>(this.stocksUrl);
  }

  public save(stock: Stock) {
    return this.http.post<Stock>(this.stocksUrl, stock);
  }
}
