import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MessageService } from 'primeng/api';
import { Style } from '../model/style.model';
import { StyleService } from '../service/style.service';

@Component({
  selector: 'app-style-list',
  templateUrl: './style-list.component.html',
  styleUrls: ['./style-list.component.scss'],
  providers: [MessageService]
})
export class StyleListComponent implements OnInit {

  styles: Style[] = [];
  stylesFilter: Style[] = [];
  styleIdToDelete?: number;

  page: number = 0;
  size: number = 25;
  sort: string = "name,asc";

  first: boolean = false;
  last: boolean = false;
  totalPages: number = 0;
  totalElements: number = 0;

  filterForm?: FormGroup;

  constructor(
    private styleService: StyleService,
    private messageService: MessageService,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit(): void {
    this.getAllStyles();
    this.buildForm();
  }

  private getAllStyles(){

    const filters:string | undefined = this.buildFilters();

    this.styleService.getStylesByCriteriaPaged(this.page,this.size,this.sort, filters).subscribe({
      next: (data: any) => { 
        this.styles = data.content,
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
      styleFilter: [""]
    })
  }

  private createFromForm() {
    return {
      styleFilter: this.filterForm?.get(['styleFilter'])!.value.id
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

  private buildFilters():string | undefined {
    const filters: string[] = [];
    const filter: any = this.createFromForm();

    if (filter.styleFilter) {
      filters.push("id:EQUAL:" + filter.styleFilter);
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
    this.getAllStyles();
  }

  public previousPage():void {
    this.page = this.page - 1;
    this.getAllStyles();
  }

  public searchByFilters():void {
    this.getAllStyles();
  }

  public prepareStyleToDelete(styleId: number): void {
    this.styleIdToDelete = styleId;
  }

  public deleteStyle(): void {
    if (this.styleIdToDelete){
      this.styleService.deleteStyle(this.styleIdToDelete).subscribe({
        next: (data) => {
          this.getAllStyles();
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
