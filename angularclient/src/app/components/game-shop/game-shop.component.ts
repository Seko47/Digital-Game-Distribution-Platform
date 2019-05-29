import {Component, OnInit} from '@angular/core';
import {Game} from "../../models/game";
import {GameService} from "../../services/game.service";
import {AuthService} from "../../services/auth/auth.service";

@Component({
  selector: 'app-game-shop',
  templateUrl: './game-shop.component.html',
  styleUrls: ['./game-shop.component.css']
})
export class GameShopComponent implements OnInit {

  games: Game[];

  constructor(private gameService: GameService, private auth: AuthService) {
  }

  ngOnInit() {
    this.gameService.findAll().subscribe(data => {
      data.forEach(game => {
        if (this.auth.isLogged()) {
          this.gameService.hasGame(+game.id).subscribe(result => game.purchased = result);
        } else {
          game.purchased = false;
        }
      });
      this.games = data
    });
  }

}
