
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './user-routing.module';
import { UserComponent } from './user.component';
import { SongCardComponent } from './entities/songs/song-card/song-card.component';
import { SongViewComponent } from './entities/songs/song-view/song-view.component';
import { SongStripComponent } from './entities/songs/song-strip/song-strip.component';


@NgModule({
  declarations: [
    UserComponent,
    SongCardComponent,
    SongViewComponent,
    SongStripComponent
  ],
  imports: [
    CommonModule,
    UserRoutingModule
  ]
})
export class UserModule { }
