import { Artist } from "../../artist/model/artist.model";

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
    artists?: Artist[];

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
        styleName?: string,
        artists?: Artist[]
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
        this.artists = artists
    }

}