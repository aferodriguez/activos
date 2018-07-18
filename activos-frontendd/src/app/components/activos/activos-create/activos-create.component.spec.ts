import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ActivosCreateComponent } from './activos-create.component';

describe('ActivosCreateComponent', () => {
  let component: ActivosCreateComponent;
  let fixture: ComponentFixture<ActivosCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ActivosCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ActivosCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
