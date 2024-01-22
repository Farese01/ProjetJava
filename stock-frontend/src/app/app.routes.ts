import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { StockListComponent } from './stock-list/stock-list.component';
import { StockFormComponent } from './stock-form/stock-form.component';
import {GetStockPriceByDateComponent} from "./get-stock-price-by-date/get-stock-price-by-date.component";
import {
  GetStockPricesBetweenDatesComponent
} from "./get-stock-prices-between-dates/get-stock-prices-between-dates.component";

export const routes: Routes = [
  { path: '', redirectTo: 'stocks', pathMatch: 'full' },
  { path: 'stocks', component: StockListComponent },
  { path: 'addstock', component: StockFormComponent },
  { path: 'getpricebydate', component: GetStockPriceByDateComponent},
  { path: 'getpricesbetweendates', component: GetStockPricesBetweenDatesComponent}
];


