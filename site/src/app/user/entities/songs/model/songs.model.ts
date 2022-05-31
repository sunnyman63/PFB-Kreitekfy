export class Songs{

  constructor(
    id: number , 
    name: string, 
    duration: number, 
    albumId: number, 
    albumImage: string, 
    totalViews: number,
    styleName:string,
    artistName:string

) {
    this.id = id
    this.name = name
    this.duration = duration
    this.albumId = albumId
    this.albumImage = albumImage
    this.totalViews = totalViews
    this.styleName = styleName
    this.artistName = artistName
  }
    id: number | undefined;
    name: string;
    duration: number;
    albumId: number;
    albumImage: string;
    totalViews: number;
    styleName:string;
    artistName:string;
}