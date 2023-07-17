import { TestBed } from '@angular/core/testing';

import { SalesCommissionService } from './sales-commission.service';

describe('SalesCommissionService', () => {
  let service: SalesCommissionService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SalesCommissionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
