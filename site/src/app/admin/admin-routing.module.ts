import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './admin.component';
import { SongFormComponent } from './entities/song/song-form/song-form.component';
import { SongListComponent } from './entities/song/song-list/song-list.component';

const routes: Routes = [
  { path: '', component: AdminComponent },
  { path: 'songs', component: SongListComponent },
  { path: 'songs/:idSong/update', component: SongFormComponent },
  { path: 'songs/create', component: SongFormComponent }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
