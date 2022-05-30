import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserComponent } from './user.component';
import { MostViewComponent } from './entities/songs/mostView/mostView.component';

const routes: Routes = [
  { path: '', component: MostViewComponent },
  { path: '', component: UserComponent }];

@NgModule({

  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
