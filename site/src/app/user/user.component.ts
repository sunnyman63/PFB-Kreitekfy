import { Component, OnInit } from '@angular/core';
import { Song } from '../admin/entities/song/model/song.model';
import { SongsService } from './entities/songs/service/songs.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {

  title? : string;
  songsTopRated: Song[] = [];

  constructor(private songService: SongsService) {}

  ngOnInit(): void {
    this.getTopRated();
  }

  private getTopRated(): void{
    this.title = "Las mas valoradas"
    this.songService.getTopRated().subscribe({
      next: (songRest) => {
        this.songsTopRated = songRest;
      },
      error: (err) => {}
    })
  }

}
