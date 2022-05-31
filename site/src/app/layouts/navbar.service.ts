import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Song } from '../admin/entities/song/model/song.model';

@Injectable({
  providedIn: 'root'
})
export class NavbarService {

  constructor(private http:HttpClient) { }
  public getAllSongs(filters?: string){
    let  urlEndpoint: string = "http://localhost:8080/api/songs/songs?styleName="+ filters;
    if (filters){
      urlEndpoint = urlEndpoint + "?filter=" + filters;
    }
    return this.http.get<Song[]>(urlEndpoint);
  }

}
