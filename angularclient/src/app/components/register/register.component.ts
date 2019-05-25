import {Component, OnInit} from '@angular/core';
import {User} from "../../models/user";
import {ActivatedRoute, Router} from "@angular/router";
import {RegisterService} from "../../services/register.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  user: User;

  constructor(private route: ActivatedRoute, private router: Router, private registerService: RegisterService) {
    this.user = new User();
  }

  onSubmit() {
    this.registerService.save(this.user).subscribe(user => {
      alert("Register success");
      this.router.navigate(['/login']);
    }, error1 => alert("Register failed"));
  }

  ngOnInit() {
  }

}
