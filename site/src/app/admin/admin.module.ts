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
    ReactiveFormsModule,
    ToastModule,
    MessageModule,
    MessagesModule
  ]
})
export class AdminModule { }
