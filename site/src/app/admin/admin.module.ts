import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ReactiveFormsModule } from '@angular/forms';

import { AutoCompleteModule } from 'primeng/autocomplete';
import { ToastModule } from 'primeng/toast';
import { MessagesModule } from 'primeng/messages';
import { MessageModule } from 'primeng/message';

import { AdminRoutingModule } from './admin-routing.module';
import { AdminComponent } from './admin.component';
import { SongFormComponent } from './entities/song/song-form/song-form.component';
import { SongListComponent } from './entities/song/song-list/song-list.component';
import { StyleListComponent } from './entities/style/style-list/style-list.component';
import { StyleFormComponent } from './entities/style/style-form/style-form.component';
import { ArtistListComponent } from './entities/artist/artist-list/artist-list.component';
import { ArtistFormComponent } from './entities/artist/artist-form/artist-form.component';

@NgModule({
  declarations: [
    AdminComponent,
    SongFormComponent,
    SongListComponent,
    StyleListComponent,
    StyleFormComponent,
    ArtistListComponent,
    ArtistFormComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    AutoCompleteModule,
    ReactiveFormsModule,
    ToastModule,
    MessageModule,
    MessagesModule
  ]
})
export class AdminModule { }
