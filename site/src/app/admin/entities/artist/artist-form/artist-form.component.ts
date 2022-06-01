import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { MessageService } from 'primeng/api';
import { Artist } from '../model/artist.model';
import { ArtistService } from '../service/artist.service';

@Component({
  selector: 'app-artist-form',
  templateUrl: './artist-form.component.html',
  styleUrls: ['./artist-form.component.scss'],
  providers: [MessageService]
})

export class ArtistFormComponent implements OnInit {

  mode: "NEW" | "UPDATE" = "NEW";
  textButton: "Crear" | "Modificar" = "Crear";
  artistId?: number;

  artist?: Artist;
  artistForm?: FormGroup;

  constructor(
    private route: ActivatedRoute,
    private messageService: MessageService,
    private artistService: ArtistService,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit(): void {

    this.buildForm();

    const entryParam: string = this.route.snapshot.paramMap.get("idArtist") ?? "new";

    if(entryParam !== "new"){
      this.artistId = + this.route.snapshot.paramMap.get("idArtist")!;
      console.log(this.artistId)
      this.mode = "UPDATE";
      this.textButton = "Modificar";
      this.getArtistById(this.artistId!);
    }else{
      this.mode = "NEW";
      this.textButton = "Crear";
      this.initializeStyle();
    }

  }

  private buildForm(): void {
    this.artistForm = this.formBuilder.group({
      id: [{value: undefined, disabled: true}],
      name: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(100)]]
    })
  }

  private updateForm(artist: Artist): void{
    this.artistForm?.patchValue({
      id: artist.id,
      name: artist.name
    })
  }

  private createFromForm() {
    //To Do
    return {
      ...this.artist,
      id: this.artistForm?.get(['id'])!.value,
      name: this.artistForm?.get(['name'])!.value
    }
  }

  private initializeStyle() {
    this.artist = new Artist(undefined,"");
  }

  private getArtistById(artistId: number) {
    this.artistService.getArtist(artistId).subscribe({
      next: (data) => {
        this.artist = data;
        this.updateForm(this.artist);
      },
      error: (err) => {
        //TO DO
      }
    });
  }

  saveArtist(){
    const styleToSave: any = this.createFromForm();
    if (this.mode === "NEW") {
      this.insertArtist(styleToSave);
    }

    if (this.mode === "UPDATE") {
      this.updateArtist(styleToSave);
    }
  }

  private insertArtist(artist: Artist) {
    this.artistService.insertArtist(artist).subscribe({
      next: (artistInsert) => {
        this.toast("success","Operacion realizada con exito", "Artista creado");
      },
      error: (err) => {
        this.toast("error", "Fallo con el servidor", "");
      }
    })
  }

  private updateArtist(artist: Artist) {
    this.artistService.updateArtist(artist).subscribe({
      next: (artistUpdate) => {
        this.toast("success","Operacion realizada con exito", "Artista actualizado");
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
