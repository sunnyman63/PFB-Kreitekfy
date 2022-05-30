import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SongCardListComponent } from './song-card-list.component';

describe('SongCardListComponent', () => {
  let component: SongCardListComponent;
  let fixture: ComponentFixture<SongCardListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SongCardListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SongCardListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
