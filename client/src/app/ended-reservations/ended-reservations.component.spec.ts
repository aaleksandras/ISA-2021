import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EndedReservationsComponent } from './ended-reservations.component';

describe('EndedReservationsComponent', () => {
  let component: EndedReservationsComponent;
  let fixture: ComponentFixture<EndedReservationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EndedReservationsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EndedReservationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
