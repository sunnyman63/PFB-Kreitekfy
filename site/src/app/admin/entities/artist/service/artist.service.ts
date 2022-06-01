import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Artist } from '../model/artist.model';

@Injectable({
  providedIn: 'root'
})
export class ArtistService {

  constructor(private http: HttpClient) { }

  getArtistsByCriteriaPaged(page: number, size: number, sort: string, filters?: string): Observable<Artist[]> {
    let urlEndPoint: string = "http://localhost:8080/api/artists?page=" + page + "&size=" + size + "&sort=" + sort;
    if (filters) {
      urlEndPoint = urlEndPoint + "&filter=" + filters;
    }
    return this.http.get<Artist[]>(urlEndPoint)
  }

  getArtists(partialName?: string): Observable<Artist[]> {
    let urlEndpoint: string = "http://localhost:8080/api/artists/search";

    if(partialName) {
      urlEndpoint = urlEndpoint + "?partialName=" + partialName;
    }

    return this.http.get<Artist[]>(urlEndpoint);
  }

  getArtist(artistId: number): Observable<Artist> {
    const urlEndPoint: string = "http://localhost:8080/api/artists/"+ artistId;
    return this.http.get<Artist>(urlEndPoint);
  }

  insertArtist(artist: Artist) {
    const urlEndPoint: string = "http://localhost:8080/api/artists/";
    return this.http.post<Artist>(urlEndPoint, artist);
  }

  updateArtist(artist: Artist) {
    const urlEndPoint: string = "http://localhost:8080/api/artists/";
    return this.http.patch<Artist>(urlEndPoint, artist);
  }

  deleteArtist(artistIdToDelete: number): Observable<any>{
    const urlEndPoint: string = "http://localhost:8080/api/artists/"+ artistIdToDelete;
    return this.http.delete<Artist[]>(urlEndPoint)
  }

}
