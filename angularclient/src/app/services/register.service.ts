import {Injectable} from '@angular/core';
import {Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {User} from "../models/user";

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(private router: Router, private http: HttpClient) {
  }

  public save(user: User) {
    this.http.post('http://localhost:8080/register', user).subscribe(result => alert(result), error1 => alert(error1));
  }
}
