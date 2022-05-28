import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewComentsComponent } from './view-coments.component';

describe('ViewComentsComponent', () => {
  let component: ViewComentsComponent;
  let fixture: ComponentFixture<ViewComentsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewComentsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewComentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
