import { Injectable } from '@angular/core';

const ID_KEY = 'id';
const ADMIN_KEY = 'isAdmin';
const NAME_KEY = 'name';

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  private userId: number | null;
  private userName: string | null;
  private isAdmin: boolean | null;

  constructor() {
    this.userId = Number(sessionStorage.getItem(ID_KEY));
    this.userName = sessionStorage.getItem(NAME_KEY);
    this.isAdmin = Boolean(sessionStorage.getItem(ADMIN_KEY));
   }

  public setId(id:number): void {
    window.sessionStorage.removeItem(ID_KEY);
    window.sessionStorage.setItem(ID_KEY, String(id));
    this.userId = Number(sessionStorage.getItem(ID_KEY));
  }

  public setIsAdmin(isAdmin: boolean): void {
    window.sessionStorage.removeItem(ADMIN_KEY);
    window.sessionStorage.setItem(ADMIN_KEY, String(isAdmin));
    this.isAdmin = Boolean(sessionStorage.getItem(ADMIN_KEY));
  }

  public setName(name: string): void {
    window.sessionStorage.removeItem(NAME_KEY);
    window.sessionStorage.setItem(NAME_KEY, name);
    this.userName = sessionStorage.getItem(NAME_KEY);
  }

  public getId(): number | null {
    return this.userId;
  }

  public getIsAdmin(): boolean | null {
    return this.isAdmin;
  }

  public getName(): string | null {
    return this.userName;
  }
}
