import { Component, OnInit } from '@angular/core';
import { Stock } from '../model/stock';
import { StockService } from '../service/stock-service.service';
import {CommonModule} from "@angular/common";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";



@Component({
  selector: 'app-stock-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './stock-list.component.html',
  styleUrls: ['./stock-list.component.css']
})
export class StockListComponent implements OnInit {
  private URL_STOCK = 'http://localhost:52001';
  stocks: Stock[] | undefined;

  mostSearched: any;
  constructor(private readonly httpClient: HttpClient,
              private readonly stockService:  StockService) { }

  ngOnInit(): void {
    this.getAllStocks()
      .pipe()
      .subscribe( value => {
        this.stocks = value;
      });

    this.stockService.GetMostSearchedStock().pipe()
        .subscribe( (value: any ) => {
          this.mostSearched = value|| {}; // Ensure that data is not null or undefined
            },
            (error: any) => {
              console.error('Error fetching data:', error);
            }
        );
      }
  getAllStocks(): Observable<Stock[]> {
    const url = `${this.URL_STOCK}/stock`;
    return this.httpClient.get<Stock[]>(url);
  }

  getKeyValuePairs(obj: any): { key: string, value: any }[] {
    return Object.keys(obj || {}).map(key => ({ key, value: obj[key] }));
  }
}
