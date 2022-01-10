import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteEntitiesComponent } from './delete-entities.component';

describe('DeleteEntitiesComponent', () => {
  let component: DeleteEntitiesComponent;
  let fixture: ComponentFixture<DeleteEntitiesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DeleteEntitiesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DeleteEntitiesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
