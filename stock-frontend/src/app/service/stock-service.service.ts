import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Stock } from '../model/stock';
// @ts-ignore
import { Observable } from 'rxjs/Observable';

@Injectable()
export class StockService {

  private readonly APPLICATION_JSON = 'application/json';
  private stocksUrl: string;
  private createStockUrl: string;

  constructor(private http: HttpClient) {
    this.stocksUrl = 'http://localhost:52001/stock';
    this.createStockUrl = 'http://localhost:52001/stock/create'
  }

  public findAll(): Observable<Stock[]> {
    return this.http.get<Stock[]>(this.stocksUrl);
  }

 public save(stock: Stock) {
   const headers = { 'content-type': 'application/json'};
    return this.http.post<Stock>(this.createStockUrl, stock);
  }

}
