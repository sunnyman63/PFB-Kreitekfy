import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { MessageService } from 'primeng/api';
import { Album } from '../model/album.model';
import { AlbumService } from '../service/album.service';

@Component({
  selector: 'app-album-form',
  templateUrl: './album-form.component.html',
  styleUrls: ['./album-form.component.scss'],
  providers: [MessageService]
})
export class AlbumFormComponent implements OnInit {

  mode: "NEW" | "UPDATE" = "NEW";
  textButton: "Crear" | "Modificar" = "Crear";
  albumId?: number;

  album?: Album;
  albumForm?: FormGroup;

  constructor(
    private route: ActivatedRoute,
    private messageService: MessageService,
    private albumService: AlbumService,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit(): void {

    this.buildForm();
    const entryParam: string = this.route.snapshot.paramMap.get("idAlbum") ?? "new";

    if(entryParam !== "new"){
      this.albumId = + this.route.snapshot.paramMap.get("idAlbum")!;
      this.mode = "UPDATE";
      this.textButton = "Modificar";
      this.getAlbumById(this.albumId!);
    }else{
      this.mode = "NEW";
      this.textButton = "Crear";
      this.initializeStyle();
    }

  }

  private buildForm(): void {
    this.albumForm = this.formBuilder.group({
      id: [{value: undefined, disabled: true}],
      name: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(100)]]
    })
  }

  private updateForm(album: Album): void{
    this.albumForm?.patchValue({
      id: album.id,
      name: album.name
    })

    console.log(this.album)
  }

  private createFromForm() {
    //To Do
    return {
      ...this.album,
      id: this.albumForm?.get(['id'])!.value,
      name: this.albumForm?.get(['name'])!.value,
      image: this.album?.image
    }
  }

  private initializeStyle() {
    this.album = new Album(undefined,"","","");
  }

  private getAlbumById(albumId: number) {
    this.albumService.getAlbum(albumId).subscribe({
      next: (data) => {
        this.album = data;
        this.album.imageType = "data:image/jpeg;base64";
        this.updateForm(this.album);
      },
      error: (err) => {
        //TO DO
      }
    });
  }

  saveAlbum(){
    let albumToSave: any = this.createFromForm();
    if (this.mode === "NEW") {
      this.insertAlbum(albumToSave);
    }

    if (this.mode === "UPDATE") {
      this.updateAlbum(albumToSave);
    }
  }

  private insertAlbum(album: Album) {
    this.albumService.insertAlbum(album).subscribe({
      next: (albumInsert) => {
        this.toast("success","Operacion realizada con exito", "Album creado");
      },
      error: (err) => {
        this.toast("error", "Fallo con el servidor", "");
      }
    })
  }

  private updateAlbum(album: Album) {
    this.albumService.updateAlbum(album).subscribe({
      next: (albumUpdate) => {
        this.toast("success","Operacion realizada con exito", "Album actualizado");
      },
      error: (err) => {
        this.toast("error", "Fallo con el servidor", "");
      }
    })
  }

  private getImageType(imageString: string): string {
    const imageStringParts: string[] = imageString.split(",");
    if (imageStringParts.length == 2) {
      return imageStringParts[0];
    } else {
      return "";
    }
  }

  private getImageBase64(imageString: string): string {
    const imageStringParts: string[] = imageString.split(",");
    if (imageStringParts.length == 2) {
      return imageStringParts[1];
    } else {
      return "";
    }
  }

  public includeImageInItem(event: any): void {
    const inputFile = event.target as HTMLInputElement;
    const file: File | null = inputFile.files?.item(0) ?? null;


    this.readFileAsString(file!).then(
      (result) => {
        const imageType: string = this.getImageType(result);
        const imageBase64: string = this.getImageBase64(result);

        this.album!.imageType = imageType;
        this.album!.image = imageBase64;

      },
      (error) => {
        console.log("No se pudo cargar la imagen")
      })

  }

  private readFileAsString(file: File) {
    return new Promise<string>(function(resolve, reject) {
      let reader: FileReader = new FileReader();
      reader.readAsDataURL(file)
      reader.onload = function() {
        resolve(this.result as string)
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
