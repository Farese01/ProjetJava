import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Stock } from '../model/stock';
// @ts-ignore
import { Observable } from 'rxjs/Observable';
import {Dailyprice} from "../model/dailyprice";
import {StockDate} from "../model/stock-date";

@Injectable()
export class StockService {

  private readonly APPLICATION_JSON = 'application/json';
  private stocksUrl: string;
  private createStockUrl: string;
  private stocksPriceUrl: string;
  private stocksPriceBetweenUrl: string;

  constructor(private http: HttpClient) {
    this.stocksUrl = 'http://localhost:52001/stock';
    this.createStockUrl = 'http://localhost:52001/stock/create'
    this.stocksPriceUrl = 'http://localhost:52001/stock/price'
    this.stocksPriceBetweenUrl = 'http://localhost:52001/stock/prices-between-dates'
  }

  public findAll(): Observable<Stock[]> {
    return this.http.get<Stock[]>(this.stocksUrl);
  }

 public save(stock: Stock) {
   const headers = { 'content-type': 'application/json'};
    return this.http.post<Stock>(this.createStockUrl, stock);
  }

  public findStockPriceByDate(stockDate: StockDate ): Observable<Dailyprice> {
      const headers = { 'content-type': 'application/json'};
      // @ts-ignore
      return this.http.get<Dailyprice>(this.stocksPriceUrl, stockDate);

  }
}
