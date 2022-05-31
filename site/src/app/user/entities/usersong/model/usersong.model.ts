export class usersong{

    constructor(
        id:number,
        userId:number,
        songId:number,
        personalViews:number,
        personalValorations:number
    ){

        this.id=id
        this.userId=userId
        this.songId=songId
        this.personalViews=personalViews
        this.personalValorations= personalValorations
    }

    id:number;
    userId:number;
    songId:number;
    personalViews:number;
    personalValorations:number;
}