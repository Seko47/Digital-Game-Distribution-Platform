import {Component, OnInit} from '@angular/core';
import {Game} from "../../models/game";
import {GameService} from "../../services/game.service";
import {Router} from "@angular/router";
import {AuthService} from "../../services/auth/auth.service";

@Component({
  selector: 'app-game-list',
  templateUrl: './game-list.component.html',
  styleUrls: ['./game-list.component.css']
})
export class GameListComponent implements OnInit {

  games: Game[];

  constructor(private gameService: GameService, private router: Router, private auth: AuthService) {
    if(!auth.hasAdminRole())
    {
      router.navigate(['/']);
    }
  }

  ngOnInit() {
    this.gameService.findAll().subscribe(data => this.games = data );
  }

}
