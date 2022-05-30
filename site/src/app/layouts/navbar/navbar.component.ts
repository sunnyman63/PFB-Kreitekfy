import { Component, OnInit } from '@angular/core';
import { UserDTO } from 'src/app/shared/models/UserDTO.model';
import { SessionService } from 'src/app/shared/service/session.service';
import { UserService } from 'src/app/shared/service/user.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  users: Array<UserDTO> = [];
  isAdmin: boolean = false;

  constructor(
    private userService: UserService,
    private sessionService: SessionService
  ) { }

  ngOnInit(): void {
    this.getUsers();
  }

  private getUsers(): void {
    this.userService.getUsers().subscribe({
      next:(data) => {
        this.users = data;
        console.log(this.users);
      }
    })
  }

  selectUser(user: UserDTO): void {
    this.sessionService.setId(user.id);
    this.sessionService.setIsAdmin(user.admin);
    this.sessionService.setName(user.name);
    if(user.admin) {
      this.isAdmin = true;
    } else {
      this.isAdmin = false;
    }
  }



}
