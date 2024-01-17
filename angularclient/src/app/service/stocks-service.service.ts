import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Stocks } from '../model/stocks';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class stocksservice {

  private stocksUrl: string;

  constructor(private http: HttpClient) {
    this.stocksUrl = 'http://localhost:8080/stocks';
  }

  public findAll(): Observable<Stocks[]> {
    return this.http.get<Stocks[]>(this.stocksUrl);
  }

  public save(stock: Stocks) {
    return this.http.post<Stocks>(this.stocksUrl, stock);
  }
}
