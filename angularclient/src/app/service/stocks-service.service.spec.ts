import { TestBed } from '@angular/core/testing';

import { StocksServiceService } from './stocks-service.service';

describe('StocksServiceService', () => {
  let service: StocksServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(StocksServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
