import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NavbarService {

  styleId: number = 0;
  isLogged: boolean = false;
  private emitChangeSourceStyle = new Subject<number>();
  private emitChangeSourceIsLogged = new Subject<boolean>();
  styleEmitted = this.emitChangeSourceStyle.asObservable();
  isLoggedEmitted = this.emitChangeSourceIsLogged.asObservable();

  constructor() { }

  public getActualStyleId(): number{
    return this.styleId;
  }

  public getActualIsLogged(): boolean {
    return this.isLogged;
  }

  emitStyle(style: number): void {
    this.styleId = style;
    this.emitChangeSourceStyle.next(style);
  }

  emitIsLogged(isLogged: boolean): void {
    this.isLogged = isLogged;
    this.emitChangeSourceIsLogged.next(isLogged);
  }

}
