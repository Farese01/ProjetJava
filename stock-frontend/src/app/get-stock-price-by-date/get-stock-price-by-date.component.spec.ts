import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetStockPriceByDateComponent } from './get-stock-price-by-date.component';

describe('GetStockPriceByDateComponent', () => {
  let component: GetStockPriceByDateComponent;
  let fixture: ComponentFixture<GetStockPriceByDateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GetStockPriceByDateComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(GetStockPriceByDateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
