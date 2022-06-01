import { NavbarService } from './../../shared/service/navbar.service';
import { Subject } from 'rxjs';
import { StyleService } from './../../admin/entities/style/service/style.service';
import { Component, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { UserDTO } from 'src/app/shared/models/UserDTO.model';
import { SessionService } from 'src/app/shared/service/session.service';
import { UserService } from 'src/app/shared/service/user.service';
import { Style } from 'src/app/admin/entities/style/model/style.model';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})

export class NavbarComponent implements OnInit {

  @Input() styleId:Subject<number> = new Subject<number>();

  users: Array<UserDTO> = [];
  isAdmin: boolean = false;
  styles: Style[] = [];
  currentStyle:number=0
  

  constructor(
    private userService: UserService,
    private sessionService: SessionService,
    private router: Router,
    private styleService: StyleService,
    private navbarService:NavbarService
  ) { }

  ngOnInit(): void {
    this.getUsers();
    if(this.sessionService.getIsAdmin != null) {
      this.isAdmin = this.sessionService.getIsAdmin()!;
    }

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
    this.router.navigate(['/']);
    if(user.admin) {
      this.isAdmin = true;
    } else {
      this.isAdmin = false;
    }
  }

  getStyles(event: any): void {
    let styleSearch: string | undefined;

    if(event?.query) {
      styleSearch = event.query;
    }

    this.styleService.getStyles(styleSearch).subscribe({
      next: (stylesFilter) => { this.styles = stylesFilter },
      error: (err) => { 
        //TO DO
       }
    });
  }

  styleSelected():void{
    this.navbarService.setActualStyleId(this.styles[0].id!)
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
    this.router.onSameUrlNavigation = 'reload';
    this.router.navigate(['/']);
  }



}
