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
  stock: Stock;
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private stockService: StockService) {
    this.stock = new Stock();
  }

  ngOnInit(): void {
    this.loadForm();
  }

  loadForm(): void {
    this.form = new FormGroup({
      id: new FormControl(null),
      symbol: new FormControl('', Validators.required),
    });
  }


  onSubmit(): void {
    if (this.form.valid) {
      this.submitted = true;
      this.stock = this.form.value;
      this.stockService.save(this.stock).subscribe(res => {
        this.router.navigate(['/stocks']);
      });

    }
  }
}
