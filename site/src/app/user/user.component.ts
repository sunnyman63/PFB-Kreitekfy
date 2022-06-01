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

  titleTopRated: string = "Lo mejor valorado";
  titleTopViewed: string = "Lo más sonado";
  titleNewest : string = "Novedades";
  titleForU: string = "Para ti"
  songsTopRated: Songs[] = [];
  songsNewest: Songs[] = [];
  songsTopViewed: Songs[] = [];
  songsForU: Songs[] = [];
  isLogged: boolean = false;
  userId: number | null = null;
  currentStyleId:number=0

  constructor(
    private songService: SongsService,
    private sessionService: SessionService,
    private navbarService: NavbarService
    ) {
      navbarService.styleEmitted.subscribe(style => {
        if(style != this.currentStyleId) {
          this.ngOnInit();
        }
      });
      navbarService.isLoggedEmitted.subscribe(isLogged => {
        if(isLogged != this.isLogged) {
          navbarService.emitStyle(0);
          this.ngOnInit();
        }
      })
    }

  ngOnInit(): void {
    this.currentStyleId=this.navbarService.getActualStyleId();
    this.userId = this.sessionService.getId();
    this.getTopRated();
    this.getTopNewest();
    this.getTopViewed();
    if(this.userId != 0) {
      this.getForU();
    }
    
  }

  private getTopRated(): void{

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

    this.songService.getForU(this.userId!,this.currentStyleId).subscribe({
      next: (songRest) => {
        this.songsForU = songRest;
      },
      error: (err) => {}
    })
    }

}
