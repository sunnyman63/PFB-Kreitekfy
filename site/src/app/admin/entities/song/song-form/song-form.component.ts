import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { MessageService } from 'primeng/api';
import { Album } from '../../album/model/album.model';
import { AlbumService } from '../../album/service/album.service';
import { Style } from '../../style/model/style.model';
import { StyleService } from '../../style/service/style.service';
import { Song } from '../model/song.model';
import { SongService } from '../service/song.service';

@Component({
  selector: 'app-song-form',
  templateUrl: './song-form.component.html',
  styleUrls: ['./song-form.component.scss'],
  providers: [MessageService]
})
export class SongFormComponent implements OnInit {

  mode: "NEW" | "UPDATE" = "NEW";
  textButton: "Crear" | "Modificar" = "Crear";
  songId?: number;
  song?: Song;
  selectedStyle?: Style;
  styles: Style[] = [];
  selectedAlbum?: Album;
  albumns: Album[] = [];

  songForm?: FormGroup;

  constructor(
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private songService: SongService,
    private styleService: StyleService,
    private albumService: AlbumService,
    private messageService: MessageService
  ) { }

  ngOnInit(): void {

    this.buildForm();

    const entryParam: string = this.route.snapshot.paramMap.get("idSong") ?? "new";

    if(entryParam !== "new"){
      this.songId = + this.route.snapshot.paramMap.get("idSong")!;
      this.mode = "UPDATE";
      this.textButton = "Modificar";
      this.getProductById(this.songId!);
    }else{
      this.mode = "NEW";
      this.textButton = "Crear";
      this.initializeProduct();
    }
  }

  private buildForm(): void {
    this.songForm = this.formBuilder.group({
      id: [{value: undefined, disabled: true}],
      name: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(100)]],
      duration: ['', [Validators.maxLength(2000)]],
      style: [0, [Validators.required, Validators.min(0)]],
      album: [undefined, Validators.required]
    })
  }

  private updateForm(song: Song): void{
    this.songForm?.patchValue({
      id: song.id,
      name: song.name,
      duration: song.duration,
      style: new Style(song.styleId!, song.styleName!),
      album: new Album(song.albumId!,song.albumName!, song.albumImage!)
    })
  }

  private createFromForm() {
    //To Do
    return {
      ...this.song,
      id: this.songForm?.get(['id'])!.value,
      name: this.songForm?.get(['name'])!.value,
      duration: this.songForm?.get(['duration'])!.value,
      styleId: this.songForm?.get(['style'])!.value.id,
      styleName: this.songForm?.get(['style'])!.value.name,
      albumId: this.songForm?.get(['album'])!.value.id,
      albumName: this.songForm?.get(['album'])!.value.name,
      albumImage: this.songForm?.get(['album'])!.value.image
    }
  }

  private initializeProduct() {
    this.song = new Song(undefined,"",0);
  }

  private getProductById(itemId: number) {
    this.songService.getSong(itemId).subscribe({
      next: (data) => {
        this.song = data;
        this.updateForm(this.song);
        this.selectedStyle = new Style(data.styleId!, data.styleName!),
        this.selectedAlbum = new Album(data.albumId!,data.albumName!,data.albumImage!)
      },
      error: (err) => {
        //TO DO
      }
    });
  }

  getStyles(event: any): void {
    let styleSearch: string | undefined;

    if(event?.query) {
      styleSearch = event.query;
    }

    this.styleService.getStyles(styleSearch).subscribe({
      next: (stylesFilter) => { this.styles = stylesFilter },
      error: (err) => { 
        //TO DO
       }
    });
  }

  styleSelected(): void {
    this.song!.styleId = this.selectedStyle?.id;
    this.song!.styleName = this.selectedStyle?.name;
  }

  styleUnselected(): void {
    this.song!.styleId = undefined;
    this.song!.styleName = undefined;
  }

  getAlbums(event: any): void {
    let albumSearch: string | undefined;

    if(event?.query) {
      albumSearch = event.query;
    }

    this.albumService.getAlbums(albumSearch).subscribe({
      next: (albumsFilter) => { this.albumns = albumsFilter },
      error: (err) => { 
        //TO DO
       }
    });
  }

  albumSelected(): void {
    this.song!.albumId = this.selectedAlbum?.id;
    this.song!.albumName = this.selectedAlbum?.name;
    this.song!.albumImage = this.selectedAlbum?.image;
  }

  albumUnselected(): void {
    this.song!.albumId = undefined;
    this.song!.albumName = undefined;
    this.song!.albumImage = undefined;
  }

  saveSong(){
    const itemToSave: any = this.createFromForm();
    if (this.mode === "NEW") {
      this.insertSong(itemToSave);
    }

    if (this.mode === "UPDATE") {
      this.updateSong(itemToSave);
    }
  }

  private insertSong(song: Song) {
    this.songService.insertSong(song).subscribe({
      next: (songInsert) => {
        this.toast("success","Operacion realizada con exito", "Producto Creado");
      },
      error: (err) => {
        this.toast("error", "Fallo con el servidor", "");
      }
    })
  }

  private updateSong(song: Song) {
    this.songService.updateSong(song).subscribe({
      next: (songUpdate) => {
        this.toast("success","Operacion realizada con exito", "Producto actualizado");
      },
      error: (err) => {
        this.toast("error", "Fallo con el servidor", "");
      }
    })
  }

  private toast(severity: string, summary: string, message: string): void {
    this.messageService.add({
      severity: severity, 
      summary: summary, 
      detail: message
    }); 
  }

}
