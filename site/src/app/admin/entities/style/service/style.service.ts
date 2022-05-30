import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Style } from '../model/style.model';

@Injectable({
  providedIn: 'root'
})
export class StyleService {

  constructor(private http: HttpClient) { }

  getStyles(partialName?: string): Observable<Style[]> {
    let urlEndpoint: string = "http://localhost:8080/api/styles";

    if(partialName) {
      urlEndpoint = urlEndpoint + "?partialName=" + partialName;
    }

    return this.http.get<Style[]>(urlEndpoint);
  }
}
