import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Album } from '../model/album.model';

@Injectable({
  providedIn: 'root'
})
export class AlbumService {

  constructor(private http: HttpClient) { }

  getAlbums(partialName?: string): Observable<Album[]> {
    let urlEndpoint: string = "http://localhost:8080/api/albums/search";

    if(partialName) {
      urlEndpoint = urlEndpoint + "?partialName=" + partialName;
    }

    return this.http.get<Album[]>(urlEndpoint);
  }
}
