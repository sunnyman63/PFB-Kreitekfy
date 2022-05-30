import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './user-routing.module';
import { UserComponent } from './user.component';
import { SongViewComponent } from './entities/songs/song-view/song-view.component';


@NgModule({
  declarations: [
    UserComponent,
    SongViewComponent
  ],
  imports: [
    CommonModule,
    UserRoutingModule
  ]
})
export class UserModule { }
