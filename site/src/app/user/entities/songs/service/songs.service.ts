import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Songs } from '../model/songs.model';

@Injectable({
  providedIn: 'root'
})
export class SongsService {

  constructor(private http: HttpClient ) { }

  public getAllNewestSongs(): Observable<Songs[]>{
    const urlEndpoint: string = "http://localhost:8080/api/songs/newests";
    return this.http.get<Songs[]>(urlEndpoint);
  }

  public getOneSong(id:number): Observable<Songs>{
    const urlEndpoint: string = "http://localhost:8080/api/songs/"+id;
    return this.http.get<Songs>(urlEndpoint);
  }

  public getTopRated(): Observable<Songs[]> {
    const urlEndpoint: string = "http://localhost:8080/api/songs/top-rated";
    return this.http.get<Songs[]>(urlEndpoint);
  }

  getAllTopViewed() {
    const urlEndpoint: string = "http://localhost:8080/api/songs/top-view";
    return this.http.get<Songs[]>(urlEndpoint);
  }

}
