import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Artist } from '../model/artist.model';

@Injectable({
  providedIn: 'root'
})
export class ArtistService {

  constructor(private http: HttpClient) { }

  getArtists(partialName?: string): Observable<Artist[]> {
    let urlEndpoint: string = "http://localhost:8080/api/artists/search";

    if(partialName) {
      urlEndpoint = urlEndpoint + "?partialName=" + partialName;
    }

    return this.http.get<Artist[]>(urlEndpoint);
  }
}
