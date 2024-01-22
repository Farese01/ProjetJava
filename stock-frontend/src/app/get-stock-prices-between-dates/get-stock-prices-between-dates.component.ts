import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {PriceBetween} from "../model/price-between";
import {StockService} from '../service/stock-service.service';
import {Router} from "@angular/router";
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {StockDate} from "../model/stock-date";
import {CommonModule} from "@angular/common";
import {StockValues} from "../model/stock-values";
import {Dailyprice} from "../model/dailyprice";

@Component({
  selector: 'app-get-stock-price-between-dates',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, FormsModule],
  templateUrl: './get-stock-prices-between-dates.component.html',
  styleUrl: './get-stock-prices-between-dates.component.css'
})
export class GetStockPricesBetweenDatesComponent {

  priceBetween :PriceBetween | any;
  formPrice: FormGroup = new FormGroup({});
  submitted = false;
  dailyPrices: Dailyprice[] | any;

  constructor(private readonly httpClient: HttpClient,
              private readonly stockService:  StockService,
              private router: Router) { }



  loadForm(): void {
    this.formPrice = new FormGroup({
      symbol: new FormControl('', Validators.required),
      dateFrom: new FormControl('', Validators.required),
      dateTo: new FormControl('', Validators.required)
    });
  }
  ngOnInit(): void {
    this.loadForm();
    this.dailyPrices = [];

  }

  onSubmit(): void {
    if (this.formPrice.valid) {
      this.submitted = true;
      this.priceBetween = this.formPrice.value;
      this.stockService.findStockPriceBetweenDates(this.priceBetween).pipe()
        .subscribe( (value: StockValues ) => {
            console.log('Form submitted!', value);
          this.dailyPrices = value;
        });
      console.log('Form submitted!', this.dailyPrices);


    }
  }
  get f() { return this.formPrice.controls; }



}
