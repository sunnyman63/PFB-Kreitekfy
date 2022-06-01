import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './admin.component';
import { ArtistFormComponent } from './entities/artist/artist-form/artist-form.component';
import { ArtistListComponent } from './entities/artist/artist-list/artist-list.component';
import { SongFormComponent } from './entities/song/song-form/song-form.component';
import { SongListComponent } from './entities/song/song-list/song-list.component';
import { StyleFormComponent } from './entities/style/style-form/style-form.component';
import { StyleListComponent } from './entities/style/style-list/style-list.component';

const routes: Routes = [
  { path: '', component: AdminComponent },
  { path: 'songs', component: SongListComponent },
  { path: 'songs/create', component: SongFormComponent },
  { path: 'songs/:idSong/update', component: SongFormComponent },
  { path: 'styles', component: StyleListComponent },
  { path: 'styles/create', component: StyleFormComponent },
  { path: 'styles/:idStyle/update', component: StyleFormComponent },
  { path: 'artists', component: ArtistListComponent },
  { path: 'artists/create', component: ArtistFormComponent },
  { path: 'artists/:idArtist/update', component: ArtistFormComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
