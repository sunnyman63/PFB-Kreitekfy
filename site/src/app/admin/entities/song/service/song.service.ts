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

  getSong(songId: number): Observable<Song>{
    const urlEndPoint: string = "http://localhost:8080/api/songs/"+ songId;
    return this.http.get<Song>(urlEndPoint);
  }

  insertSong(song: Song) {
    const urlEndPoint: string = "http://localhost:8080/api/songs/";
    return this.http.post<Song>(urlEndPoint, song);
  }

  updateSong(song: Song) {
    const urlEndPoint: string = "http://localhost:8080/api/songs/";
    return this.http.patch<Song>(urlEndPoint, song);
  }

  deleteSong(songIdToDelete: number): Observable<any>{
    const urlEndPoint: string = "http://localhost:8080/api/songs/"+ songIdToDelete;
    return this.http.delete<Song[]>(urlEndPoint)
  }

}
