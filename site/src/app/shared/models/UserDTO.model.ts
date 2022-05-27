import { UserSongDTO } from "./UserSongDTO.model";

export class UserDTO {
    id: number;
    admin: boolean;
    name: string;
    songsData: UserSongDTO[];


  constructor(
      id: number, 
      admin: boolean, 
      name: string,
      songsData: UserSongDTO[]
      ) {
    this.id = id;
    this.admin = admin;
    this.name = name;
    this.songsData = songsData;
  }
}