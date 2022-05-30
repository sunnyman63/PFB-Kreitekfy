import { Songs } from './../model/songs.model';
import { Song } from './../../../../admin/entities/song/model/song.model';
import { SongsService } from './../service/songs.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-song-view',
  templateUrl: './song-view.component.html',
  styleUrls: ['./song-view.component.scss']
})
export class SongViewComponent implements OnInit {
  idSong?:number;
  Song?:Songs;

  constructor(
    private route:ActivatedRoute,
    private SongsService:SongsService
    ) { }

  ngOnInit(): void {

    this.idSong =+ this.route.snapshot.paramMap.get("idSong")!;
    this.getSong(this.idSong);
  }

  private getSong(idSong:number){
    this.SongsService.getOneSong(idSong).subscribe({
      next: (SongRequest) => {this.Song = SongRequest, console.log(this.Song)},
      error: (err) => {this.handleError(err);}

    })
  }
  handleError(err: any) {
    throw new Error('Method not implemented.');
  }

}
