import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
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
  albumsFilter: Album[] = [];
  albumIdToDelete?: number;

  page: number = 0;
  size: number = 25;
  sort: string = "name,asc";

  first: boolean = false;
  last: boolean = false;
  totalPages: number = 0;
  totalElements: number = 0;

  filterForm?: FormGroup;

  constructor(
    private albumService: AlbumService,
    private messageService: MessageService,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit(): void {
    this.buildForm();
    this.getAllAlbums();
  }

  private getAllAlbums(){

    const filters:string | undefined = this.buildFilters();

    this.albumService.getAlbumsByCriteriaPaged(this.page,this.size,this.sort, filters).subscribe({
      next: (data: any) => {
        this.albums = data.content;
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

  private buildForm(): void {
    this.filterForm = this.formBuilder.group({
      albumFilter: [""]
    })
  }

  private createFromForm() {
    return {
      albumFilter: this.filterForm?.get(['albumFilter'])!.value.id
    }
  }

  private buildFilters():string | undefined {
    const filters: string[] = [];
    const filter: any = this.createFromForm();

    if (filter.albumFilter) {
      filters.push("id:EQUAL:" + filter.albumFilter);
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
