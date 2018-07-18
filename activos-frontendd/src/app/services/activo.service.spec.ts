import { TestBed, inject } from '@angular/core/testing';

import { ActivoService } from './activo.service';

describe('ActivoService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ActivoService]
    });
  });

  it('should be created', inject([ActivoService], (service: ActivoService) => {
    expect(service).toBeTruthy();
  }));
});
