import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {AuthService} from "../../services/auth/auth.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  model: any = {};

  constructor(private auth: AuthService, private route: ActivatedRoute, private router: Router, private http: HttpClient) {
  }

  ngOnInit() {
    sessionStorage.removeItem('token');
    localStorage.removeItem('adminRole');
  }

  login() {
    let url = 'http://localhost:8080/login';
    this.http.post<Observable<boolean>>(url, {
      username: this.model.username,
      password: this.model.password
    }).subscribe(isValid => {
      if (isValid) {
        sessionStorage.setItem(
          'token',
          btoa(this.model.username + ':' + this.model.password)
        );
        this.auth.fetchAdminRole();
        alert("Authentication success");
        this.router.navigate(['/games']);
      } else {
        alert("Authentication failed");
      }
    });
  }

}
