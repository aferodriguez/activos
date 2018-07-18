import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ActivosListComponent } from './activos-list.component';

describe('ActivosListComponent', () => {
  let component: ActivosListComponent;
  let fixture: ComponentFixture<ActivosListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ActivosListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ActivosListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
