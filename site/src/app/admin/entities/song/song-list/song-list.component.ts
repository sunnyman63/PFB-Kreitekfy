import { ThisReceiver } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MessageService } from 'primeng/api';
import { Album } from '../../album/model/album.model';
import { AlbumService } from '../../album/service/album.service';
import { Artist } from '../../artist/model/artist.model';
import { ArtistService } from '../../artist/service/artist.service';
import { Style } from '../../style/model/style.model';
import { StyleService } from '../../style/service/style.service';
import { Song } from '../model/song.model';
import { SongService } from '../service/song.service';

@Component({
  selector: 'app-song-list',
  templateUrl: './song-list.component.html',
  styleUrls: ['./song-list.component.scss']
})
export class SongListComponent implements OnInit {

  songs: Song[] = [];
  stylesFilter: Style[] = [];
  artistFilter: Artist[] = [];
  albumsFilter: Album[] = [];
  songsFilter: Song[] = [];

  page: number = 0;
  size: number = 25;
  sort: string = "name,asc";

  first: boolean = false;
  last: boolean = false;
  totalPages: number = 0;
  totalElements: number = 0;

  nameFilter?: string;
  priceFilter?: number;

  songIdToDelete?: number;

  filterForm?: FormGroup;

  constructor(
    private songService: SongService,
    private artistService: ArtistService,
    private styleService: StyleService,
    private albumService: AlbumService,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit(): void {
    this.buildForm();
    this.getAllSongs();
  }

  private getAllSongs(): void {

    const filters:string | undefined = this.buildFilters();

    this.songService.getPaginatedFilteredSongs(this.page, this.size, this.sort, filters).subscribe({
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

  private buildForm(): void {
    this.filterForm = this.formBuilder.group({
      styleFiler: [""],
      artistFilter: [""],
      albumFilter: [""],
      songFilter: [""]
    })
  }

  private createFromForm() {
    return {
      styleFiler: this.filterForm?.get(['styleFiler'])!.value.id,
      artistFilter: this.filterForm?.get(['artistFilter'])!.value.id,
      albumFilter: this.filterForm?.get(['albumFilter'])!.value.id,
      songFilter: this.filterForm?.get(['songFilter'])!.value.id
    }
  }

  getStyles(event: any): void {
    let categoriesSearch: string | undefined;

    if(event?.query) {
      categoriesSearch = event.query;
    }

    this.styleService.getStyles(categoriesSearch).subscribe({
      next: (stylesFilter) => { this.stylesFilter = stylesFilter },
      error: (err) => { }
    });
  }

  getArtists(event: any): void {
    let categoriesSearch: string | undefined;

    if(event?.query) {
      categoriesSearch = event.query;
    }

    this.artistService.getArtists(categoriesSearch).subscribe({
      next: (categoriesFilter) => { this.artistFilter = categoriesFilter },
      error: (err) => {  }
    });
  }

  getAlbums(event: any): void {
    let categoriesSearch: string | undefined;

    if(event?.query) {
      categoriesSearch = event.query;
    }

    this.albumService.getAlbums(categoriesSearch).subscribe({
      next: (categoriesFilter) => { this.albumsFilter = categoriesFilter },
      error: (err) => { }
    });
  }

  getSongs(event: any): void {
    let categoriesSearch: string | undefined;

    if(event?.query) {
      categoriesSearch = event.query;
    }

    this.songService.getSongs(categoriesSearch).subscribe({
      next: (categoriesFilter) => { this.songsFilter = categoriesFilter },
      error: (err) => {  }
    });
  }

  private buildFilters():string | undefined {
    const filters: string[] = [];
    const filter: any = this.createFromForm();

    if (filter.styleFiler) {
      filters.push("style.id:EQUAL:" + filter.styleFiler);
    }

    if(filter.artistFilter) {
      filters.push("artists.id:EQUAL:" + filter.artistFilter);
    }

    if (filter.albumFilter) {
      filters.push("album.id:EQUAL:" + filter.albumFilter);
    }

    if (filter.songFilter) {
      filters.push("id:EQUAL:" + filter.songFilter);
    }

    if (filters.length >0) {

      let globalFilters: string = "";
      for (let filter of filters) {
        globalFilters = globalFilters + filter + ",";
      }
      globalFilters = globalFilters.substring(0, globalFilters.length-1);
      return globalFilters;

    } else {
      return undefined;
    }
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
