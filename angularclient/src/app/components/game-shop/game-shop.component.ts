import {Component, OnInit} from '@angular/core';
import {Game} from "../../models/game";
import {GameService} from "../../services/game.service";

@Component({
  selector: 'app-game-shop',
  templateUrl: './game-shop.component.html',
  styleUrls: ['./game-shop.component.css']
})
export class GameShopComponent implements OnInit {

  games: Game[];

  constructor(private gameService: GameService) {
  }

  ngOnInit() {
    this.gameService.findAll().subscribe(data => this.games = data);
  }

}
