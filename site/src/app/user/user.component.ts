import { SessionService } from 'src/app/shared/service/session.service';
import { Component, OnInit } from '@angular/core';
import { Songs } from './entities/songs/model/songs.model';
import { SongsService } from './entities/songs/service/songs.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {


  titleTopRated: string = "Lo mejor valorado";
  titleTopViewed: string = "Lo más sonado";
  titleNewest : string = "Novedades";
  titleForU: string = "Para ti"
  songsTopRated: Songs[] = [];
  songsNewest: Songs[] = [];
  songsTopViewed: Songs[] = [];
  songsForU: Songs[] = [];
  userId: number = this.sessionService.getId()!;

  constructor(
    private songService: SongsService,
    private sessionService: SessionService
    ) {}

  ngOnInit(): void {
    this.getTopRated();
    this.getTopNewest();
    this.getTopViewed();
    this.getForU();
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

  private getForU(): void{

    this.songService.getForU(this.userId).subscribe({
      next: (songRest) => {
        this.songsForU = songRest;
      },
      error: (err) => {}
    })
    }


}
