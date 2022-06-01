import { Songs } from "src/app/user/entities/songs/model/songs.model";

export class totalAlbum {
 
    id: number | undefined;
    name: string;
    image?: string;
    imageType?: string;
    songs?:Songs[]


    constructor(id: number | undefined, name: string, image?: string, imageType?: string, songs?:Songs[]) {
        this.id = id
        this.name = name
        this.imageType = imageType
        this.image = image
        this.songs = songs
    }

    getImage(){
        return this.imageType+","+this.image;
    }

}