import { Router } from '@angular/router';
import { totalAlbum } from './../../../../../admin/entities/album/model/totalalbum.model';
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Album } from 'src/app/admin/entities/album/model/album.model';
import { AlbumService } from 'src/app/admin/entities/album/service/album.service';
import { Songs } from '../../model/songs.model';

@Component({
  selector: 'app-album-list',
  templateUrl: './album-list.component.html',
  styleUrls: ['./album-list.component.scss']
})
export class AlbumListComponent implements OnInit {

  @Input() albumId!:number;
  @Output("newItemEvent") newEventEmiter = new EventEmitter<string>();

  songs:Songs[]=[];
  album!:totalAlbum

  constructor(
    private albumService:AlbumService,
    private route: Router
  ) { }

  ngOnInit(): void {
    this.getSongsByAlbum()

  }
  getSongsByAlbum(){
    this.albumService.getAlbum(this.albumId).subscribe({
      next: (data) => {this.album = data
      console.log(this.album)},
      error: (err) => {this.handleError(err);
      }

    })
    
  }
  handleError(err: any) {
    throw new Error('Method not implemented.');
  }

  onclick(id:number){
    this.route.navigate(['/song/'+id])
    this.ngOnInit()
    this.newEventEmiter.emit();
  }

}
