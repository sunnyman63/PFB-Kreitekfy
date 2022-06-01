import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
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
  artistFilter: Artist[] = [];

  page: number = 0;
  size: number = 25;
  sort: string = "name,asc";

  first: boolean = false;
  last: boolean = false;
  totalPages: number = 0;
  totalElements: number = 0;

  filterForm?: FormGroup;

  constructor(
    private artistService: ArtistService,
    private messageService: MessageService,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit(): void {
    this.getAllArtist();
    this.buildForm();
  }

  private getAllArtist(){

    const filters:string | undefined = this.buildFilters();

    this.artistService.getArtistsByCriteriaPaged(this.page,this.size,this.sort, filters).subscribe({
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

  private buildForm(): void {
    this.filterForm = this.formBuilder.group({
      artistFilter: [""]
    })
  }

  private createFromForm() {
    return {
      artistFilter: this.filterForm?.get(['artistFilter'])!.value.id
    }
  }

  private buildFilters():string | undefined {
    const filters: string[] = [];
    const filter: any = this.createFromForm();

    if(filter.artistFilter) {
      filters.push("id:EQUAL:" + filter.artistFilter);
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
