import {Component, OnInit} from '@angular/core';
import {Game} from "../../models/game";
import {ActivatedRoute, Router} from "@angular/router";
import {GameService} from "../../services/game.service";

@Component({
  selector: 'app-game-edit',
  templateUrl: './game-edit.component.html',
  styleUrls: ['./game-edit.component.css']
})
export class GameEditComponent implements OnInit {

  game: Game;
  id: number;

  constructor(private route: ActivatedRoute, private router: Router, private gameService: GameService) {
  }

  onSubmit() {
    this.gameService.update(this.game).subscribe(result => this.gotoGameList(), result => alert("Failed to edit game"));
  }

  gotoGameList() {
    this.router.navigate(['/admin/games']);
  }

  ngOnInit() {
    this.route.params.subscribe(params => this.id = params['id']);
    this.gameService.findByID(this.id).subscribe(result => this.game = result);
  }

}
