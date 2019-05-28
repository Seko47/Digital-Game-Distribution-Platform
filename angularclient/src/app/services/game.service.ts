import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Game} from "../models/game";
import {Observable} from "rxjs";
import {Router} from "@angular/router";

@Injectable()
export class GameService {

  gamesUrl: string;

  constructor(private router: Router, private http: HttpClient) {
    this.gamesUrl = 'http://localhost:8080/games';
  }

  public findAll(): Observable<Game[]> {
    return this.http.get<Game[]>(this.gamesUrl);
  }

  public findByID(id: number): Observable<Game> {
    return this.http.get<Game>(this.gamesUrl + "/" + id);
  }

  public save(game: Game) {
    let headers: HttpHeaders = new HttpHeaders({
      'Authorization': 'Basic ' + sessionStorage.getItem('token')
    });

    let options = {headers: headers};
    return this.http.post<Game>(this.gamesUrl, game, options);
  }

  public update(game: Game) {
    let headers: HttpHeaders = new HttpHeaders({
      'Authorization': 'Basic ' + sessionStorage.getItem('token')
    });

    let options = {headers: headers};

    return this.http.put<Game>(this.gamesUrl + "/" + game.id, game, options);
  }

  public delete(id: number) {
    let headers: HttpHeaders = new HttpHeaders({
      'Authorization': 'Basic ' + sessionStorage.getItem('token')
    });

    let options = {headers: headers};

    return this.http.delete<Game>(this.gamesUrl + "/" + id, options);
  }

  public hasGame(id: number) {
    let headers: HttpHeaders = new HttpHeaders({
      'Authorization': 'Basic ' + sessionStorage.getItem('token')
    });

    let options = {headers: headers};

    return this.http.get<boolean>("http://localhost:8080/orders/has/" + id, options);
  }
}
