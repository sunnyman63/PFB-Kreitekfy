import { Component, OnInit } from '@angular/core';
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
  styleIdToDelete?: number;

  page: number = 0;
  size: number = 25;
  sort: string = "name,asc";

  first: boolean = false;
  last: boolean = false;
  totalPages: number = 0;
  totalElements: number = 0;

  constructor(
    private styleService: StyleService,
    private messageService: MessageService
  ) { }

  ngOnInit(): void {
    this.getAllStyles();
  }

  private getAllStyles(){
    this.styleService.getStylesByCriteriaPaged(this.page,this.size,this.sort).subscribe({
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
