export class Songs{

  constructor(
    id: number , 
    name: string, 
    duration: number, 
    albumId: number, 
    albumImage: string, 
    totalViews: number,
    styleName:string,

) {
    this.id = id
    this.name = name
    this.duration = duration
    this.albumId = albumId
    this.albumImage = albumImage
    this.totalViews = totalViews
    this.styleName = styleName
  }
    id: number | undefined;
    name: string;
    duration: number;
    albumId: number;
    albumImage: string;
    totalViews: number;
    styleName:string;
}