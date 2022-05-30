import { Component, OnInit } from '@angular/core';
import { SongService } from 'src/app/admin/entities/song/service/song.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {


    styleFilter?: string;
  navbarService: any;


  constructor(    private songService: SongService) { }

  ngOnInit(): void {
  }
public searchByStyle():void{
  this.getAllItems();
}

private buildFilters(): string | undefined {
  const filters: string[] = [];
  if(this.styleFilter){
    filters.push("name:MATCH:" + this.styleFilter);
  }
  if (filters.length > 0){
let globalFilters: string = "";
for(let filter of filters){
  globalFilters = globalFilters + filter + ",";
}
globalFilters = globalFilters.substring(0, globalFilters.length-1);
return globalFilters;
  } else {
    return undefined;
  }
}

private getAllItems():void {
const filters: string | undefined = this.buildFilters();
this.navbarService.getAllSongs(this.styleFilter).subscribe();




}
}
