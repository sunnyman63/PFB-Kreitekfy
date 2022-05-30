import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserDTO } from '../models/UserDTO.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  url: string = 'http://localhost:8080/api/users';

  constructor(private httpClient: HttpClient) { }

  public getUsers(): Observable<Array<UserDTO>> {
    return this.httpClient.get<Array<UserDTO>>(this.url);
  }
}
