import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewPenaltiesComponent } from './view-penalties.component';

describe('ViewPenaltiesComponent', () => {
  let component: ViewPenaltiesComponent;
  let fixture: ComponentFixture<ViewPenaltiesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewPenaltiesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewPenaltiesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
