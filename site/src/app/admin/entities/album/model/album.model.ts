export class Album {
 
    id: number | undefined;
    name: string;
    image?: string;
    imageType?: string;

    constructor(id: number | undefined, name: string, image?: string, imageType?: string) {
        this.id = id
        this.name = name
        this.imageType = imageType
        this.image = image
    }

    getImage(){
        return this.imageType+","+this.image;
    }

}