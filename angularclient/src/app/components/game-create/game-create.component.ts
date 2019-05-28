import {Component, OnInit} from '@angular/core';
import {Game} from "../../models/game";
import {ActivatedRoute, Router} from "@angular/router";
import {GameService} from "../../services/game.service";

@Component({
  selector: 'app-game-create',
  templateUrl: './game-create.component.html',
  styleUrls: ['./game-create.component.css']
})
export class GameCreateComponent implements OnInit {

  game: Game;

  constructor(private route: ActivatedRoute, private router: Router, private gameService: GameService) {
    this.game = new Game();
  }

  onSubmit() {
    this.gameService.save(this.game).subscribe(result => this.gotoGameList(), result => alert("Failed to add new game"));
  }

  gotoGameList() {
    this.router.navigate(['admin/games']);
  }

  ngOnInit() {
  }

}
