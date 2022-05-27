import { Component, OnInit } from '@angular/core';
import { Songs } from '../model/songs.model';
import { SongsService } from '../service/songs.service';

@Component({
  selector: 'app-songs-list',
  templateUrl: './songs-list.component.html',
  styleUrls: ['./songs-list.component.scss']
})
export class SongsListComponent implements OnInit {

  songs: Songs[]=[];

  constructor(private songsService: SongsService) { }

  ngOnInit(): void {
    this.getSongs();
  }
  private getSongs(): void{
    this.songsService.getAllSongs().subscribe({
      next: (SongsRequest: any) => {this.songs = SongsRequest.content; },
      error: (err) => {this.handleError(err);}
    }) 
  }
  private handleError(error: any):void{
    console.log(error);
  }

}
