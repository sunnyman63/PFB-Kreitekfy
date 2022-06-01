import { Component, Input, OnInit } from '@angular/core';
import { Songs } from '../model/songs.model';


@Component({
  selector: 'app-song-strip',
  templateUrl: './song-strip.component.html',
  styleUrls: ['./song-strip.component.scss']
})
export class SongStripComponent implements OnInit {

  @Input() title?: string;
  @Input() songs?: Songs[];

  constructor() { }

  ngOnInit(): void {
    console.log(this.songs);
  }

}
