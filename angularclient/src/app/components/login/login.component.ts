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
    this.auth.logout();
  }

  login() {
    this.auth.login(this.model.username, this.model.password).subscribe(isValid => {
      if (isValid) {
        sessionStorage.setItem(
          'token',
          btoa(this.model.username + ':' + this.model.password)
        );
        this.auth.fetchAdminRole();
        localStorage.setItem('username', this.model.username);
        alert("You are logged in");
        this.router.navigate(['/']);
      } else {
        alert("Invalid login and/or password");
      }
    }, () => alert("Invalid login and/or password"));
  }

}
