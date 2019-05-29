import {Component, OnInit} from '@angular/core';
import {Game} from "../../models/game";
import {ActivatedRoute, Router} from "@angular/router";
import {GameService} from "../../services/game.service";
import {AuthService} from "../../services/auth/auth.service";

@Component({
  selector: 'app-game-details',
  templateUrl: './game-details.component.html',
  styleUrls: ['./game-details.component.css']
})
export class GameDetailsComponent implements OnInit {

  game: Game;
  id: number;
  purchased: boolean;

  constructor(private activatedRoute: ActivatedRoute, private router: Router, private gameService: GameService, private auth: AuthService) {
    this.activatedRoute.params.subscribe(params => this.id = params['id']);
    this.gameService.findByID(this.id).subscribe(result => this.game = result);
    if (this.auth.isLogged()) {
      this.gameService.hasGame(this.id).subscribe(result => this.purchased = result);
    } else {
      this.purchased = false;
    }
  }

  ngOnInit() {
  }

  buy() {
    this.gameService.buy(this.id).subscribe(result=>{this.purchased = true; alert("purchased");}, error1 => alert("error"));
  }


}
