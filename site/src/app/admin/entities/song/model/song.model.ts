export class Song {

    id: number | undefined;
    name: string;
    duration: number;
    inclusionDate?: Date;
    totalViews?: number;
    albumId?: number;
    albumName?: string;
    albumImage?: string;
    styleId?: number;
    styleName?: string;

    constructor(
        id: number | undefined,
        name: string,
        duration: number,
        inclusionDate?: Date,
        totalViews?: number,
        albumId?: number,
        albumName?: string,
        albumImage?: string,
        styleId?: number,
        styleName?: string
    ) {
        this.id = id
        this.name = name
        this.duration = duration
        this.inclusionDate = inclusionDate
        this.totalViews = totalViews
        this.albumId = albumId
        this.albumName = albumName
        this.albumImage = albumImage
        this.styleId = styleId
        this.styleName = styleName
    }

}