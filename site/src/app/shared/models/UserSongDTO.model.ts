export class UserSongDTO {
    userName: string;
    songId: number;
    userId: number;
    songName: string;
    personalViews: number;
    personalValorations: number;

    constructor(
        userName: string,
        songId: number,
        userId: number,
        songName: string,
        personalViews: number,
        personalValorations: number
    ) {
        this.userName = userName;
        this.songId = songId;
        this.userId = userId;
        this.songName = songName;
        this.personalViews = personalViews;
        this.personalValorations = personalValorations;
    }
}