import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Song } from '../../admin/entities/song/model/song.model';

@Injectable({
  providedIn: 'root'
})
export class NavbarService {

  styleId:number=0

  constructor(private http:HttpClient,
     ) { }

  public getActualStyleId(){
    return this.styleId;
  }

  setActualStyleId(styleId:number){
    this.styleId=styleId
  }

}
