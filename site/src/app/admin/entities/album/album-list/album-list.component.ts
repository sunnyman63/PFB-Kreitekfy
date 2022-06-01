import { Component, OnInit } from '@angular/core';
import { MessageService } from 'primeng/api';
import { Album } from '../model/album.model';
import { AlbumService } from '../service/album.service';

@Component({
  selector: 'app-album-list',
  templateUrl: './album-list.component.html',
  styleUrls: ['./album-list.component.scss'],
  providers: [MessageService]
})
export class AlbumListComponent implements OnInit {

  albums: Album[] = [];
  albumIdToDelete?: number;

  page: number = 0;
  size: number = 25;
  sort: string = "name,asc";

  first: boolean = false;
  last: boolean = false;
  totalPages: number = 0;
  totalElements: number = 0;

  constructor(
    private albumService: AlbumService,
    private messageService: MessageService
  ) { }

  ngOnInit(): void {
    this.getAllAlbums();
  }

  private getAllAlbums(){
    this.albumService.getAlbumsByCriteriaPaged(this.page,this.size,this.sort).subscribe({
      next: (data: any) => { 
        this.albums = data.content,
        this.first = data.first;
        this.last = data.last;
        this.totalPages = data.totalPages;
        this.totalElements = data.totalElements;
      } ,
      error: (error) => { 
        //To do 
      }
    });
  }

  public nextPage():void {
    this.page = this.page + 1;
    this.getAllAlbums();
  }

  public previousPage():void {
    this.page = this.page - 1;
    this.getAllAlbums();
  }

  public searchByFilters():void {
    this.getAllAlbums();
  }

  public prepareAlbumToDelete(styleId: number): void {
    this.albumIdToDelete = styleId;
  }

  public deleteAlbum(): void {
    if (this.albumIdToDelete){
      this.albumService.deleteAlbum(this.albumIdToDelete).subscribe({
        next: (data) => {
          this.getAllAlbums();
        },
        error: (err) => {
          this.toast("error", "Imposible eliminar, primero borre las canciones de este estilo", "");
        }
      })
    }
  }

  private toast(severity: string, summary: string, message: string): void {
    this.messageService.add({
      severity: severity, 
      summary: summary, 
      detail: message
    }); 
  }

}
