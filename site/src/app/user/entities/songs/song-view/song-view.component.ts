import { SessionService } from 'src/app/shared/service/session.service';
import { usersong } from './../../usersong/model/usersong.model';
import { UsersongService } from './../../usersong/service/usersong.service';
import { Songs } from './../model/songs.model';
import { SongsService } from './../service/songs.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { SongsListComponent } from '../songs-list/songs-list.component';

@Component({
  selector: 'app-song-view',
  templateUrl: './song-view.component.html',
  styleUrls: ['./song-view.component.scss']
})
export class SongViewComponent implements OnInit {
  idSong?:number;
  Song?:Songs;
  usersong={
    songId : this.idSong!,
    userId : this.sessionService.getId()!,
    id: 0,
    personalViews:0,
    personalValorations:0

  }

  constructor(
    private route:ActivatedRoute,
    private SongsService:SongsService,
    private usersongService: UsersongService,
    private sessionService: SessionService
    ) { }

  ngOnInit(): void {

    this.idSong =+ this.route.snapshot.paramMap.get("idSong")!;
    this.getSong(this.idSong);
    
  }

  private getSong(idSong:number){
    this.SongsService.getOneSong(idSong).subscribe({
      next: (SongRequest) => {this.Song = SongRequest, console.log(this.Song),
        this.usersong.songId=this.Song.id!,
        this.createUserSong();},
      error: (err) => {this.handleError(err);
      }

    })
  }

  handleError(err: any) {
    throw new Error('Method not implemented.');
  }

  createUserSong(){
    this.usersongService.postUserSong(this.usersong).subscribe({
      next: (data) => {this.usersong = data, console.log(data)
    }});
  }

  onClick(){

      this.usersongService.updateuserSong(this.usersong).subscribe({
        next: (data) => {this.usersong = data, console.log(this.usersong)},
      })
    }
  

  vote(rate:number){
    this.usersong.personalValorations=rate

    console.log(this.usersong)
    this.usersongService.updateUserSongNote(this.usersong).subscribe({
      next: (data) => {this.usersong = data}
    })
  }

}
