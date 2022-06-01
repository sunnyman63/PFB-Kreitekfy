import { Subject } from 'rxjs';
import { NavbarService } from '../shared/service/navbar.service';
import { SessionService } from 'src/app/shared/service/session.service';
import { Component, Input, OnInit, Output } from '@angular/core';
import { Songs } from './entities/songs/model/songs.model';
import { SongsService } from './entities/songs/service/songs.service';


@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {

  @Output() styleId!: Subject<number>

  titleTopRated: string = "Lo mejor valorado";
  titleTopViewed: string = "Lo más sonado";
  titleNewest : string = "Novedades";
  titleForU: string = "Para ti"
  songsTopRated: Songs[] = [];
  songsNewest: Songs[] = [];
  songsTopViewed: Songs[] = [];
  songsForU: Songs[] = [];
  userId: number = this.sessionService.getId()!;
  currentStyleId:number=0

  constructor(
    private songService: SongsService,
    private sessionService: SessionService,
    private navbarService: NavbarService
    ) {}

  ngOnInit(): void {
    this.currentStyleId=this.navbarService.getActualStyleId()
    console.log("qwer")
    this.getTopRated();
    this.getTopNewest();
    this.getTopViewed();
    this.getForU();
  }

  private getTopRated(): void{
    console.log(this.currentStyleId);

    this.songService.getTopRated(this.currentStyleId).subscribe({
      next: (songRest) => {
        this.songsTopRated = songRest;
      },
      error: (err) => {}
    })
  }
  private getTopNewest(): void{

    this.songService.getAllNewestSongs(this.currentStyleId).subscribe({
      next: (songRest) => {
        this.songsNewest = songRest;
      },
      error: (err) => {}
    })
  }

  private getTopViewed(): void{

    this.songService.getAllTopViewed(this.currentStyleId).subscribe({
      next: (songRest) => {
        this.songsTopViewed = songRest;
      },
      error: (err) => {}
    })
  }

  private getForU(): void{

    this.songService.getForU(this.userId,this.currentStyleId).subscribe({
      next: (songRest) => {
        this.songsForU = songRest;
      },
      error: (err) => {}
    })
    }

    getStyleId():void{

      this.styleId.subscribe((styleId:number)=>{
        this.currentStyleId=styleId
      })
  
    }

}
