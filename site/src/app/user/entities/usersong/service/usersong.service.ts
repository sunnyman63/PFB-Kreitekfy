import { usersong } from './../model/usersong.model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsersongService {

  constructor(private http: HttpClient ) { }

  public updateuserSong(usersong:usersong):Observable<usersong>{
    const urlEndpoint: string = "http://localhost:8080/api/usersong";
    return this.http.put<usersong>(urlEndpoint, usersong);
  }
  public postUserSong(usersong:usersong):Observable<usersong>{
    const urlEndpoint: string = "http://localhost:8080/api/usersong";
    return this.http.post<usersong>(urlEndpoint, usersong);
  }
  public updateUserSongNote(usersong:usersong){
    const urlEndpoint: string = "http://localhost:8080/api/usersong/rate";
    return this.http.put<usersong>(urlEndpoint, usersong);
  }

}
