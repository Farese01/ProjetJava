import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SuggestNextDayTrendComponent } from './suggest-next-day-trend.component';

describe('SuggestNextDayTrendComponent', () => {
  let component: SuggestNextDayTrendComponent;
  let fixture: ComponentFixture<SuggestNextDayTrendComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SuggestNextDayTrendComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SuggestNextDayTrendComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
