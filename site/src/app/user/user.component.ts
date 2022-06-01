import { Component, OnInit } from '@angular/core';

import { Songs } from './entities/songs/model/songs.model';
import { SongsService } from './entities/songs/service/songs.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {


  titleTopRated: string = "Lo mejor valorados";
  titleTopViewed: string = "Lo más sonado";
  titleNewest : string = "Novedades";
  songsTopRated: Songs[] = [];
  songsNewest: Songs[] = [];
  songsTopViewed: Songs[] = [];

  constructor(private songService: SongsService) {}

  ngOnInit(): void {
    this.getTopRated();
    this.getTopNewest();
    this.getTopViewed();
  }

  private getTopRated(): void{

    this.songService.getTopRated().subscribe({
      next: (songRest) => {
        this.songsTopRated = songRest;
      },
      error: (err) => {}
    })
  }
  private getTopNewest(): void{

    this.songService.getAllNewestSongs().subscribe({
      next: (songRest) => {
        this.songsNewest = songRest;
      },
      error: (err) => {}
    })
  }

  private getTopViewed(): void{

    this.songService.getAllTopViewed().subscribe({
      next: (songRest) => {
        this.songsTopViewed = songRest;
      },
      error: (err) => {}
    })
  }

}
