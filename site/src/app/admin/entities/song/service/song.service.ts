import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Song } from '../model/song.model';

@Injectable({
  providedIn: 'root'
})
export class SongService {

  constructor(private http: HttpClient) { }

  getAllSongs(): Observable<Song[]>{
    const urlEndPoint: string = "http://localhost:8080/api/songs";
    return this.http.get<Song[]>(urlEndPoint)
  }

  deleteSong(songIdToDelete: number): Observable<any>{
    const urlEndPoint: string = "http://localhost:8080/api/songs/"+ songIdToDelete;
    return this.http.get<Song[]>(urlEndPoint)
  }

}
