import { Component, OnInit } from '@angular/core';
import { Song } from '../model/song.model';
import { SongService } from '../service/song.service';

@Component({
  selector: 'app-song-list',
  templateUrl: './song-list.component.html',
  styleUrls: ['./song-list.component.scss']
})
export class SongListComponent implements OnInit {

  songs: Song[] = [];

  page: number = 0;
  size: number = 20;
  sort: string = "name,asc";

  first: boolean = false;
  last: boolean = false;
  totalPages: number = 0;
  totalElements: number = 0;

  nameFilter?: string;
  priceFilter?: number;

  songIdToDelete?: number;

  constructor(private songService: SongService) { }

  ngOnInit(): void {
    this.getAllSongs();
  }

  private getAllSongs(): void {
    this.songService.getAllSongs().subscribe({
      next: (data: any) => { 
        this.songs = data.content,
        this.first = data.first;
        this.last = data.last;
        this.totalPages = data.totalPages;
        this.totalElements = data.totalElements;  
      },
      error: () => {}
    })
  }

  public nextPage():void {
    this.page = this.page + 1;
    this.getAllSongs();
  }

  public previousPage():void {
    this.page = this.page - 1;
    this.getAllSongs();
  }

  public searchByFilters():void {
    this.getAllSongs();
  }

  public prepareItemToDelete(songId: number): void {
    this.songIdToDelete = songId;
  }

  public deleteItem(): void {
    if (this.songIdToDelete){
      this.songService.deleteSong(this.songIdToDelete).subscribe({
        next: (data) => {
          this.getAllSongs();
        },
        error: (err) => {} 
      })
    }
  }

}
