import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {GameService} from "../../services/game.service";
import {AuthService} from "../../services/auth/auth.service";

@Component({
  selector: 'app-game-delete',
  templateUrl: './game-delete.component.html',
  styleUrls: ['./game-delete.component.css']
})
export class GameDeleteComponent implements OnInit {

  id: number;

  constructor(private activatedRoute: ActivatedRoute, private router: Router, private gameService: GameService, private auth: AuthService) {
    if(!auth.hasAdminRole())
    {
      router.navigate(['/']);
      return;
    }
    this.activatedRoute.params.subscribe(params => this.id = params['id']);
    this.gameService.delete(this.id).subscribe(result => this.router.navigate(["/admin/games"]), error1 => {this.router.navigate(["/admin/games"]); alert("At the moment you can not delete the game");});
  }

  ngOnInit() {
  }

}
