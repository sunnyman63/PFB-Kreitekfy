import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Album } from '../model/album.model';

@Injectable({
  providedIn: 'root'
})
export class AlbumService {

  constructor(private http: HttpClient) { }

  getAlbumsByCriteriaPaged(page: number, size: number, sort: string, filters?: string): Observable<Album[]> {
    let urlEndPoint: string = "http://localhost:8080/api/albums?page=" + page + "&size=" + size + "&sort=" + sort;
    if (filters) {
      urlEndPoint = urlEndPoint + "&filter=" + filters;
    }
    return this.http.get<Album[]>(urlEndPoint)
  }

  getAlbums(partialName?: string): Observable<Album[]> {
    let urlEndpoint: string = "http://localhost:8080/api/albums/search";

    if(partialName) {
      urlEndpoint = urlEndpoint + "?partialName=" + partialName;
    }

    return this.http.get<Album[]>(urlEndpoint);
  }

  getAlbum(albumId: number): Observable<Album> {
    const urlEndPoint: string = "http://localhost:8080/api/albums/"+ albumId;
    return this.http.get<Album>(urlEndPoint);
  }

  insertAlbum(album: Album) {
    const urlEndPoint: string = "http://localhost:8080/api/albums/";
    return this.http.post<Album>(urlEndPoint, album);
  }

  updateAlbum(album: Album) {
    const urlEndPoint: string = "http://localhost:8080/api/albums/";
    return this.http.patch<Album>(urlEndPoint, album);
  }

  deleteAlbum(albumIdToDelete: number): Observable<any>{
    const urlEndPoint: string = "http://localhost:8080/api/albums/"+ albumIdToDelete;
    return this.http.delete<Album[]>(urlEndPoint)
  }
  
}
