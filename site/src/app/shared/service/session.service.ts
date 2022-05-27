import { Injectable } from '@angular/core';

const ID_KEY = 'id';
const ADMIN_KEY = 'isAdmin';
const NAME_KEY = 'name';

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  constructor() { }

  public setId(id:number): void {
    window.sessionStorage.removeItem(ID_KEY);
    window.sessionStorage.setItem(ID_KEY, String(id));
  }

  public setIsAdmin(isAdmin: boolean): void {
    window.sessionStorage.removeItem(ADMIN_KEY);
    window.sessionStorage.setItem(ADMIN_KEY, String(isAdmin));
  }

  public setName(name: string): void {
    window.sessionStorage.removeItem(NAME_KEY);
    window.sessionStorage.setItem(NAME_KEY, name);
  }

  public getId(): number | null {
    let id: string | null = sessionStorage.getItem(ID_KEY);
    if(id != null) {
      return Number(id);
    }
    return null;
  }

  public getIsAdmin(): boolean | null {
    let id: string | null = sessionStorage.getItem(ADMIN_KEY);
    if(id != null) {
      return Boolean(id);
    }
    return null;
  }

  public getName(): string | null {
    return sessionStorage.getItem(NAME_KEY);
  }
}
