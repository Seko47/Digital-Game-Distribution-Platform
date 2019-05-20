import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {GameService} from "../../services/game.service";
import {Game} from "../../models/game";

@Component({
  selector: 'app-game-form',
  templateUrl: './game-form.component.html',
  styleUrls: ['./game-form.component.css']
})
export class GameFormComponent implements OnInit {

  game: Game;

  constructor(private route: ActivatedRoute, private router: Router, private gameService: GameService) {
    this.game = new Game();
  }

  onSubmit() {
    this.gameService.save(this.game).subscribe(result => this.gotoGameList(), result => alert("Failed to add new game"));
  }

  gotoGameList() {
    this.router.navigate(['/games']);
  }

  ngOnInit() {
  }
}
