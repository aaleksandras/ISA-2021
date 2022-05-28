import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoyalityProgramComponent } from './loyality-program.component';

describe('LoyalityProgramComponent', () => {
  let component: LoyalityProgramComponent;
  let fixture: ComponentFixture<LoyalityProgramComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LoyalityProgramComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LoyalityProgramComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
