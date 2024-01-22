import {Component} from '@angular/core';
import { StockService } from '../service/stock-service.service';
import { Stock } from '../model/stock';
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {NgClass, NgIf} from "@angular/common";

@Component({
  selector: 'app-suggest-next-day-trend',
  standalone: true,
  imports: [
    FormsModule,
    NgIf,
    ReactiveFormsModule,
    NgClass
  ],
  templateUrl: './suggest-next-day-trend.component.html',
  styleUrl: './suggest-next-day-trend.component.css'
})
export class SuggestNextDayTrendComponent {

  form: FormGroup = new FormGroup({});
  submitted = false;

  suggestion : string | any;
  stockSuggestion: Stock | any;
  constructor(private readonly httpClient: HttpClient,
              private readonly stockService:  StockService,
              ) { }


  ngOnInit(): void {
    this.loadForm();
  }

  loadForm(): void {
    this.form = new FormGroup({
      symbol: new FormControl('', Validators.required),
    });
  }

  onSubmit(): void {
    if (this.form.valid) {
      this.submitted = true;
      this.stockSuggestion = this.form.value;
      this.stockService.suggestNextDayTrend(this.stockSuggestion.symbol).pipe()
          .subscribe( (value: string ) => {
                this.suggestion = value|| {}; // Ensure that data is not null or undefined
              },
              (error: any) => {
                console.error('Error fetching data:', error);
              }
          );
      console.log('Form submitted!', this.form.value);

    }
  }
  get f() { return this.form.controls; }
}
