import { Component, OnInit } from '@angular/core';
import { Songs } from '../model/songs.model';
import { SongsService } from '../service/songs.service';

@Component({
  selector: 'app-songs-mostViewed',
  templateUrl: './mostView.component.html',
  styleUrls: ['./mostView.component.scss']
})
export class MostViewComponent implements OnInit {

  songs: Songs[]=[];
  pageSize: number = 5;
  constructor(private songsService: SongsService) { }

  ngOnInit(): void {
    this.getSongs();
  }
  private getSongs(): void{
    this.songsService.getAllNewestSongs().subscribe({
      next: (SongsRequest: any) => {this.songs = SongsRequest.content;
      this.pageSize = SongsRequest.pageSize },
      error: (err) => {this.handleError(err);}
    })
  }
  private handleError(error: any):void{
    console.log(error);
  }

}
