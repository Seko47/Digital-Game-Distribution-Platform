import {Component, OnInit} from '@angular/core';
import {LibraryService} from "../../services/library.service";
import {Order} from "../../models/order";
import {AuthService} from "../../services/auth/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-game-library',
  templateUrl: './game-library.component.html',
  styleUrls: ['./game-library.component.css']
})
export class GameLibraryComponent implements OnInit {

  orders: Order[];

  constructor(private libraryService: LibraryService, private router: Router, private auth: AuthService) {
    if(!auth.isLogged())
    {
      router.navigate(['/login']);
    }
  }

  ngOnInit() {
    this.libraryService.findAll().subscribe(data => this.orders = data);
  }

}
