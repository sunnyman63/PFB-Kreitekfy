import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Songs } from '../model/songs.model';

@Injectable({
  providedIn: 'root'
})
export class SongsService {

  constructor(private http: HttpClient ) { }

public getAllSongs(pageSize: number ): Observable<Songs[]>{
  const urlEndpoint: string = "http://localhost:8080/api/songs="+pageSize+"&pageSize";
  return this.http.get<Songs[]>(urlEndpoint);
}

}
