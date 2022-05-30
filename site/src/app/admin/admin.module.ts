import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AutoCompleteModule } from 'primeng/autocomplete';


import { AdminRoutingModule } from './admin-routing.module';
import { AdminComponent } from './admin.component';
import { SongFormComponent } from './entities/song/song-form/song-form.component';
import { SongListComponent } from './entities/song/song-list/song-list.component';


@NgModule({
  declarations: [
    AdminComponent,
    SongFormComponent,
    SongListComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    AutoCompleteModule,
    BrowserModule,
    BrowserAnimationsModule
  ]
})
export class AdminModule { }
