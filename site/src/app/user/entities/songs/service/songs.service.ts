import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Songs } from '../model/songs.model';

@Injectable({
  providedIn: 'root'
})
export class SongsService {

  constructor(private http: HttpClient ) { }

  public getAllNewestSongs(styleId:number): Observable<Songs[]>{
    let urlEndpoint: string = "http://localhost:8080/api/songs/newests";
    if(styleId){
      urlEndpoint = urlEndpoint + "?styleId=" + styleId;
    }
    return this.http.get<Songs[]>(urlEndpoint);
  }

  public getOneSong(id:number): Observable<Songs>{
    let urlEndpoint: string = "http://localhost:8080/api/songs/"+id;
    return this.http.get<Songs>(urlEndpoint);
  }

  public getTopRated(styleId:number): Observable<Songs[]> {
    let urlEndpoint: string = "http://localhost:8080/api/songs/top-rated";

    if(styleId){
      urlEndpoint = urlEndpoint + "?styleId=" + styleId;
    }
    return this.http.get<Songs[]>(urlEndpoint);
  }

  getAllTopViewed(styleId:number): Observable<Songs[]> {
    let urlEndpoint: string = "http://localhost:8080/api/songs/top-view";
    if(styleId){
      urlEndpoint = urlEndpoint + "?styleId=" + styleId;
    }
    return this.http.get<Songs[]>(urlEndpoint);
  }

  getForU(id:number, styleId:number ):Observable<Songs[]>{
    let urlEndpoint: string = "http://localhost:8080/api/songs/foru/"+id;
    if(styleId){
      urlEndpoint = urlEndpoint + "?styleId=" + styleId;
    }
    return this.http.get<Songs[]>(urlEndpoint);
  }

}
