import {Component, OnInit} from '@angular/core';
import {LibraryService} from "../../services/library.service";
import {Order} from "../../models/order";

@Component({
  selector: 'app-game-library',
  templateUrl: './game-library.component.html',
  styleUrls: ['./game-library.component.css']
})
export class GameLibraryComponent implements OnInit {

  orders: Order[];

  constructor(private libraryService: LibraryService) {
  }

  ngOnInit() {
    this.libraryService.findAll().subscribe(data => this.orders = data);
  }

}
