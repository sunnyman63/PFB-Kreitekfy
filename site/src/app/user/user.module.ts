
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './user-routing.module';
import { UserComponent } from './user.component';
import { MostRatedComponent } from './entities/songs/most-rated/most-rated.component';
import { SongCardComponent } from './entities/songs/song-card/song-card.component';
import { SongCardListComponent } from './entities/songs/song-card-list/song-card-list.component';
import { SongViewComponent } from './entities/songs/song-view/song-view.component';
import { ForYouComponent } from './entities/songs/for-you/for-you.component';


@NgModule({
  declarations: [
    UserComponent,
    MostRatedComponent,
    SongCardComponent,
    SongCardListComponent,
    SongViewComponent,
    ForYouComponent
  ],
  imports: [
    CommonModule,
    UserRoutingModule
  ]
})
export class UserModule { }
