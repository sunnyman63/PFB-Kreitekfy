import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { MessageService } from 'primeng/api';
import { Style } from '../model/style.model';
import { StyleService } from '../service/style.service';

@Component({
  selector: 'app-style-form',
  templateUrl: './style-form.component.html',
  styleUrls: ['./style-form.component.scss'],
  providers: [MessageService]
})
export class StyleFormComponent implements OnInit {

  mode: "NEW" | "UPDATE" = "NEW";
  textButton: "Crear" | "Modificar" = "Crear";
  styleId?: number;

  style?: Style;
  styleForm?: FormGroup;

  constructor(
    private route: ActivatedRoute,
    private messageService: MessageService,
    private styleService: StyleService,
    private formBuilder: FormBuilder
    ) { }

  ngOnInit(): void {

    this.buildForm();

    const entryParam: string = this.route.snapshot.paramMap.get("idStyle") ?? "new";

    if(entryParam !== "new"){
      this.styleId = + this.route.snapshot.paramMap.get("idStyle")!;
      this.mode = "UPDATE";
      this.textButton = "Modificar";
      this.getStyleById(this.styleId!);
    }else{
      this.mode = "NEW";
      this.textButton = "Crear";
      this.initializeStyle();
    }

  }

  private buildForm(): void {
    this.styleForm = this.formBuilder.group({
      id: [{value: undefined, disabled: true}],
      name: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(100)]]
    })
  }

  private updateForm(style: Style): void{
    this.styleForm?.patchValue({
      id: style.id,
      name: style.name
    })
  }

  private createFromForm() {
    //To Do
    return {
      ...this.style,
      id: this.styleForm?.get(['id'])!.value,
      name: this.styleForm?.get(['name'])!.value
    }
  }

  private initializeStyle() {
    this.style = new Style(undefined,"");
  }

  private getStyleById(styleId: number) {
    this.styleService.getStyle(styleId).subscribe({
      next: (data) => {
        this.style = data;
        this.updateForm(this.style);
      },
      error: (err) => {
        //TO DO
      }
    });
  }

  saveStyle(){
    const styleToSave: any = this.createFromForm();
    if (this.mode === "NEW") {
      this.insertSong(styleToSave);
    }

    if (this.mode === "UPDATE") {
      this.updateSong(styleToSave);
    }
  }

  private insertSong(style: Style) {
    this.styleService.insertStyle(style).subscribe({
      next: (styleInsert) => {
        this.toast("success","Operacion realizada con exito", "Estilo creado");
      },
      error: (err) => {
        this.toast("error", "Fallo con el servidor", "");
      }
    })
  }

  private updateSong(style: Style) {
    this.styleService.updateStyle(style).subscribe({
      next: (styleUpdate) => {
        this.toast("success","Operacion realizada con exito", "Estilo actualizado");
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
