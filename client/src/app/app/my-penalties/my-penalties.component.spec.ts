import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MyPenaltiesComponent } from './my-penalties.component';

describe('MyPenaltiesComponent', () => {
  let component: MyPenaltiesComponent;
  let fixture: ComponentFixture<MyPenaltiesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MyPenaltiesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MyPenaltiesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
