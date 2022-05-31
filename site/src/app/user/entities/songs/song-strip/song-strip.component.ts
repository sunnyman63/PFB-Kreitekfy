import { Component, Input, OnInit } from '@angular/core';
import { Song } from 'src/app/admin/entities/song/model/song.model';

@Component({
  selector: 'app-song-strip',
  templateUrl: './song-strip.component.html',
  styleUrls: ['./song-strip.component.scss']
})
export class SongStripComponent implements OnInit {

  @Input() title?: string;
  @Input() songs?: Song[];

  constructor() { }

  ngOnInit(): void {
  }

}
