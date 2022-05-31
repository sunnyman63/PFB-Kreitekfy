import { TestBed } from '@angular/core/testing';

import { UsersongService } from './usersong.service';

describe('usersongService', () => {
  let service: UsersongService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UsersongService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
