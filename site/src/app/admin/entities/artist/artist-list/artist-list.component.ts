import { Component, OnInit } from '@angular/core';
import { MessageService } from 'primeng/api';
import { Artist } from '../model/artist.model';
import { ArtistService } from '../service/artist.service';

@Component({
  selector: 'app-artist-list',
  templateUrl: './artist-list.component.html',
  styleUrls: ['./artist-list.component.scss'],
  providers: [MessageService]
})
export class ArtistListComponent implements OnInit {

  artists: Artist[] = [];
  artistIdToDelete?: number;

  page: number = 0;
  size: number = 25;
  sort: string = "name,asc";

  first: boolean = false;
  last: boolean = false;
  totalPages: number = 0;
  totalElements: number = 0;

  constructor(
    private artistService: ArtistService,
    private messageService: MessageService
  ) { }

  ngOnInit(): void {
    this.getAllArtist();
  }

  private getAllArtist(){
    this.artistService.getArtistsByCriteriaPaged(this.page,this.size,this.sort).subscribe({
      next: (data: any) => { 
        this.artists = data.content,
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
    this.getAllArtist();
  }

  public previousPage():void {
    this.page = this.page - 1;
    this.getAllArtist();
  }

  public searchByFilters():void {
    this.getAllArtist();
  }

  public prepareArtistToDelete(styleId: number): void {
    this.artistIdToDelete = styleId;
  }

  public deleteArtist(): void {
    if (this.artistIdToDelete){
      this.artistService.deleteArtist(this.artistIdToDelete).subscribe({
        next: (data) => {
          this.getAllArtist();
        },
        error: (err) => {
          this.toast("error", "Imposible eliminar, primero borre las canciones del artista", "");
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
