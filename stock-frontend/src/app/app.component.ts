import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import {HttpClientModule, provideHttpClient} from "@angular/common/http";
import {StockService} from "./service/stock-service.service";



// @ts-ignore
// @ts-ignore
// @ts-ignore
// @ts-ignore
@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, HttpClientModule],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [StockService]
})
export class AppComponent {
  title = 'stock-frontend';
}
