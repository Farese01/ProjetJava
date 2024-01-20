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

  constructor(private readonly httpClient: HttpClient) {
  }

  ngOnInit(): void {
    this.getAllStocks()
      .pipe()
      .subscribe( value => {
        this.stocks = value;
      });
  }
  getAllStocks(): Observable<Stock[]> {
    const url = `${this.URL_STOCK}/stocks`;
    return this.httpClient.get<Stock[]>(url);
  }
}
