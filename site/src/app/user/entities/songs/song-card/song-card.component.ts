import { Component, Input, OnInit } from '@angular/core';
import { Song } from 'src/app/admin/entities/song/model/song.model';

@Component({
  selector: 'app-song-card',
  templateUrl: './song-card.component.html',
  styleUrls: ['./song-card.component.scss']
})
export class SongCardComponent implements OnInit {

  @Input() song?: Song;

  constructor() { }

  ngOnInit(): void {
  }

}
