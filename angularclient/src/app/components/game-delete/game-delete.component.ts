import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {GameService} from "../../services/game.service";

@Component({
  selector: 'app-game-delete',
  templateUrl: './game-delete.component.html',
  styleUrls: ['./game-delete.component.css']
})
export class GameDeleteComponent implements OnInit {

  id: number;

  constructor(private activatedRoute: ActivatedRoute, private router: Router, private gameService: GameService) {
    this.activatedRoute.params.subscribe(params => this.id = params['id']);
    this.gameService.delete(this.id).subscribe(result => this.router.navigate(["/admin/games"]));
  }

  ngOnInit() {
  }

}
