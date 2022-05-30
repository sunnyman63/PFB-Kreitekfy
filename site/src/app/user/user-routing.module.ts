import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserComponent } from './user.component';
import { SongViewComponent } from './entities/songs/song-view/song-view.component';


const routes: Routes = [
  { path: '', component: UserComponent },
  { path: 'song/:idSong', component: SongViewComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
