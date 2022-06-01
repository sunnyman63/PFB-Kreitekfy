import { SessionService } from 'src/app/shared/service/session.service';
import { UsersongService } from './../../usersong/service/usersong.service';
import { Songs } from './../model/songs.model';
import { SongsService } from './../service/songs.service';
import { Component, OnInit, Output } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';


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
    private sessionService: SessionService,
    private router: Router
    ) { }

  ngOnInit(): void {

    this.idSong = Number(this.route.snapshot.paramMap.get("idSong")!);
    console.log(this.route.snapshot.paramMap.get("idSong"));
    console.log(this.router.url);
    this.getSong(this.idSong);
    
  }

  refresh(id: string): void {
    this.router.navigate(['/song/'+id])
    if(this.idSong != Number(id)) {
      this.ngOnInit();
    }
  }

  private getSong(idSong:number){
    this.SongsService.getOneSong(idSong).subscribe({
      next: (SongRequest) => {this.Song = SongRequest,
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
    if(this.sessionService.getId() != 0) {
      this.usersongService.postUserSong(this.usersong).subscribe({
        next: (data) => {this.usersong = data
      }});
    }
  }

  onClick(){

      this.usersongService.updateuserSong(this.usersong).subscribe({
        next: (data) => {this.usersong = data, console.log(this.usersong)
        this.Song!.totalViews++},
      })
    }
  

  vote(rate:number){
    this.usersong.personalValorations=rate

    this.usersongService.updateUserSongNote(this.usersong).subscribe({
      next: (data) => {this.usersong = data
        this.getSong(this.idSong!);}
    })
  }

}
