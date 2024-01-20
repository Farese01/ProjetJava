import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetStockPricesBetweenDatesComponent } from './get-stock-prices-between-dates.component';

describe('GetStockPricesBetweenDatesComponent', () => {
  let component: GetStockPricesBetweenDatesComponent;
  let fixture: ComponentFixture<GetStockPricesBetweenDatesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GetStockPricesBetweenDatesComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(GetStockPricesBetweenDatesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
