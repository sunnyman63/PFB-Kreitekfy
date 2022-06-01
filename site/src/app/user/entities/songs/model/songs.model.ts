import { Artist } from "src/app/admin/entities/artist/model/artist.model"

export class Songs{

  constructor(
    id: number , 
    name: string, 
    duration: number, 
    albumId: number, 
    albumImage: string, 
    totalViews: number,
    totalRate:number,
    styleName:string,
    artists: Artist[],
    albumName:string

) {
    this.id = id
    this.name = name
    this.duration = duration
    this.albumId = albumId
    this.albumImage = albumImage
    this.totalViews = totalViews
    this.styleName = styleName
    this.artists = artists
    this.totalRate= totalRate
    this.albumName=albumName
  }
    id: number | undefined;
    name: string;
    duration: number;
    albumId: number;
    albumImage: string;
    totalViews: number;
    styleName:string;
    artists: Artist[];
    totalRate: number;
    albumName:string
}