import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private loggedIn = new BehaviorSubject<boolean>(false);

  get isLoggedIn$(): Observable<boolean> {
    return this.loggedIn.asObservable();
  }

  constructor(private http: HttpClient,
              private router: Router
  ) { }

  login(data: any) {
    // api call here
    this.loggedIn.next(true);
    this.router.navigate(['/home'])
    return {
      email: data?.email
    }
  }

  logout() {
    this.loggedIn.next(false);
    this.router.navigate(['/login'])
  }
}
