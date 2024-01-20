import {Component, OnInit} from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { StockService } from '../service/stock-service.service';
import { Stock } from '../model/stock';
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import { CommonModule } from '@angular/common';
import {HttpClient} from "@angular/common/http";
@Component({
  selector: 'app-stock-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, FormsModule],
  templateUrl: './stock-form.component.html',
  styleUrls: ['./stock-form.component.css']
})
export class StockFormComponent implements OnInit{
  form: FormGroup = new FormGroup({});
  submitted = false;

  stockToBeCreated: Stock | any;
  constructor(private readonly httpClient: HttpClient,
              private readonly stockService:  StockService,
              private router: Router) { }


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
      this.stockToBeCreated = this.form.value;
      this.stockService.save(this.stockToBeCreated).subscribe(res => {
        this.router.navigate(['/']);
      });
      console.log('Form submitted!', this.form.value);

    }
  }
  get f() { return this.form.controls; }



}
