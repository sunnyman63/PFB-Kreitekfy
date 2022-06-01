import { Artist } from "src/app/admin/entities/artist/model/artist.model"

export class Songs{

  constructor(
    id: number , 
    name: string, 
    duration: number, 
    albumId: number, 
    albumImage: string, 
    totalViews: number,
    styleName:string,
    artists: Artist[]

) {
    this.id = id
    this.name = name
    this.duration = duration
    this.albumId = albumId
    this.albumImage = albumImage
    this.totalViews = totalViews
    this.styleName = styleName
    this.artists = artists
  }
    id: number | undefined;
    name: string;
    duration: number;
    albumId: number;
    albumImage: string;
    totalViews: number;
    styleName:string;
    artists: Artist[];
}