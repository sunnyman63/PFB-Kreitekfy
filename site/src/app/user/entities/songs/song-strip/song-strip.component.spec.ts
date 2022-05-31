import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SongStripComponent } from './song-strip.component';

describe('SongStripComponent', () => {
  let component: SongStripComponent;
  let fixture: ComponentFixture<SongStripComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SongStripComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SongStripComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
