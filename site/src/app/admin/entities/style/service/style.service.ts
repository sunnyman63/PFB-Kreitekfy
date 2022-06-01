import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Style } from '../model/style.model';

@Injectable({
  providedIn: 'root'
})
export class StyleService {

  constructor(private http: HttpClient) { }

  getStylesByCriteriaPaged(page: number, size: number, sort: string, filters?: string): Observable<Style[]> {
    let urlEndPoint: string = "http://localhost:8080/api/styles?page=" + page + "&size=" + size + "&sort=" + sort;
    if (filters) {
      urlEndPoint = urlEndPoint + "&filter=" + filters;
    }
    return this.http.get<Style[]>(urlEndPoint)
  }

  getStyles(partialName?: string): Observable<Style[]> {
    let urlEndPoint: string = "http://localhost:8080/api/styles/search/";

    if(partialName) {
      urlEndPoint = urlEndPoint + "?partialName=" + partialName;
    }

    return this.http.get<Style[]>(urlEndPoint);
  }

  getStyle(styleId: number): Observable<Style> {
    const urlEndPoint: string = "http://localhost:8080/api/styles/"+ styleId;
    return this.http.get<Style>(urlEndPoint);
  }

  insertStyle(style: Style) {
    const urlEndPoint: string = "http://localhost:8080/api/styles/";
    return this.http.post<Style>(urlEndPoint, style);
  }

  updateStyle(style: Style) {
    const urlEndPoint: string = "http://localhost:8080/api/styles/";
    return this.http.patch<Style>(urlEndPoint, style);
  }

  deleteStyle(styleIdToDelete: number): Observable<any>{
    const urlEndPoint: string = "http://localhost:8080/api/styles/"+ styleIdToDelete;
    return this.http.delete<Style[]>(urlEndPoint)
  }

}
