import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Stock } from '../model/stock';
// @ts-ignore
import { Observable } from 'rxjs/Observable';
import {Dailyprice} from "../model/dailyprice";
import {StockDate} from "../model/stock-date";
import {PriceBetween} from "../model/price-between";
import {StockValues} from "../model/stock-values";

@Injectable()
export class StockService {

  private readonly APPLICATION_JSON = 'application/json';
  private stocksUrl: string;
  private createStockUrl: string;
  private stocksPriceUrl: string;
  private stocksPriceBetweenUrl: string;
  private nextDayTrendUrl: string;

  constructor(private http: HttpClient) {
    this.stocksUrl = 'http://localhost:52001/stock';
    this.createStockUrl = 'http://localhost:52001/stock/create';
    this.stocksPriceUrl = 'http://localhost:52001/stock/price';
    this.stocksPriceBetweenUrl = 'http://localhost:52001/stock/prices-between-dates';
    this.nextDayTrendUrl = "http://localhost:52001/stock/nextday/";
  }

  public findAll(): Observable<Stock[]> {
    return this.http.get<Stock[]>(this.stocksUrl);
  }

 public save(stock: Stock) {
   const headers = { 'content-type': 'application/json'};
    return this.http.post<Stock>(this.createStockUrl, stock);
  }

  public findStockPriceByDate(stockDate: StockDate ): Observable<Dailyprice> {

      return this.http.get<Dailyprice>(this.stocksPriceUrl+"?symbol="+stockDate.symbol+"&date="+stockDate.date);

  }

  public findStockPriceBetweenDates(priceBetween: PriceBetween ): Observable<StockValues> {

    return this.http.get<StockValues>(this.stocksPriceBetweenUrl+"?symbol="+priceBetween.symbol+"&dateFrom="+priceBetween.dateFrom+"&dateTo="+priceBetween.dateTo);

  }

  public GetMostSearchedStock() : Observable<string> {

      return this.http.get<string>(this.stocksUrl+"/most-searched");
  }

  public suggestNextDayTrend(symbol: string) : Observable<string>{

      // @ts-ignore
      return this.http.get<string>(this.nextDayTrendUrl+symbol, { responseType: 'text' });
    }
}
