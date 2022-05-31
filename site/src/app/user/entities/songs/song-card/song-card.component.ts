import { Component, Input, OnInit } from '@angular/core';
import { Songs } from '../model/songs.model';


@Component({
  selector: 'app-song-card',
  templateUrl: './song-card.component.html',
  styleUrls: ['./song-card.component.scss']
})
export class SongCardComponent implements OnInit {

  @Input() song?: Songs;

  constructor() { }

  ngOnInit(): void {
  }

}
