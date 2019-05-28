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
    let headers: HttpHeaders = new HttpHeaders({
      'Authorization': 'Basic ' + sessionStorage.getItem('token')
    });

    let options = {headers: headers};
    return this.http.get<Game[]>(this.gamesUrl, options);
  }

  public findByID(id: number): Observable<Game> {
    let headers: HttpHeaders = new HttpHeaders({
      'Authorization': 'Basic ' + sessionStorage.getItem('token')
    });

    let options = {headers: headers};
    return this.http.get<Game>(this.gamesUrl + "/" + id, options);
  }

  public save(game: Game) {
    let headers: HttpHeaders = new HttpHeaders({
      'Authorization': 'Basic ' + sessionStorage.getItem('token')
    });

    let options = {headers: headers};
    return this.http.post<Game>(this.gamesUrl, game, options);
  }

  g

  public update(game: Game) {
    let headers: HttpHeaders = new HttpHeaders({
      'Authorization': 'Basic ' + sessionStorage.getItem('token')
    });

    let options = {headers: headers};

    return this.http.put<Game>(this.gamesUrl + "/" + game.id, game, options);
  }
}
