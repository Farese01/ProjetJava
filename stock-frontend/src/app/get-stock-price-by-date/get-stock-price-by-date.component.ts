import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Dailyprice} from "../model/dailyprice";
import { StockService } from '../service/stock-service.service';
import {Router} from "@angular/router";
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {StockDate} from "../model/stock-date";
import {CommonModule} from "@angular/common";

@Component({
  selector: 'app-get-stock-price-by-date',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, FormsModule],
  templateUrl: './get-stock-price-by-date.component.html',
  styleUrl: './get-stock-price-by-date.component.css'
})
export class GetStockPriceByDateComponent {

  dailyPrice:Dailyprice = new Dailyprice("0","0",0,0,0,0,0);
  formPrice: FormGroup = new FormGroup({});
  submitted = false;
  stockDate: StockDate | any;

  constructor(private readonly httpClient: HttpClient,
              private readonly stockService:  StockService,
              private router: Router) { }



  loadForm(): void {
    this.formPrice = new FormGroup({
      symbol: new FormControl('', Validators.required),
      date: new FormControl('', Validators.required)
    });
  }
  ngOnInit(): void {
    this.loadForm();
    this.dailyPrice = new Dailyprice("0","0",0,0,0,0,0);

  }

  onSubmit(): void {
    if (this.formPrice.valid) {
      this.submitted = true;
      this.stockDate = this.formPrice.value;
      this.stockService.findStockPriceByDate(this.stockDate).pipe()
          .subscribe( (value: Dailyprice ) => {
            this.dailyPrice = value;
          });
      console.log('Form submitted!', this.formPrice.value);


    }
  }
  get f() { return this.formPrice.controls; }



}
