import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private options: any;

  constructor(private http: HttpClient) {
  }

  public isLogged() {
    return sessionStorage.getItem('token') != null && sessionStorage.getItem('token').length >= 1;
  }

  public hasAdminRole() {
    return localStorage.getItem('adminRole') !== null && localStorage.getItem('adminRole') === 'true';
  }

  public fetchAdminRole() {
    if (!this.isLogged()) return false;
    let headers: HttpHeaders = new HttpHeaders({
      'Authorization': 'Basic ' + sessionStorage.getItem('token')
    });

    let options = {headers: headers};
    this.http.get<Boolean>('http://localhost:8080/user/is/admin', options).subscribe(result => localStorage.setItem('adminRole', String(result)));
  }

  public login(username: string, password: string) {
    let url = 'http://localhost:8080/login';
    return this.http.post<Observable<boolean>>(url, {
      username: username,
      password: password
    });
  }

  public logout() {
    sessionStorage.removeItem('token');
    localStorage.removeItem('adminRole');
    localStorage.removeItem('username');
  }
}
