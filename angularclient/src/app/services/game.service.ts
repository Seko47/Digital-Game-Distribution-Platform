import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Game} from "../models/game";
import {Observable} from "rxjs";
import {Router} from "@angular/router";

@Injectable()
export class GameService {

  private gamesUrl: string;

  constructor(private router: Router, private http: HttpClient) {
    this.gamesUrl = 'http://localhost:8080/games';
  }

  public findAll(): Observable<Game[]>{

    if(sessionStorage.getItem('token') == null || sessionStorage.getItem('token') == '')
    {
      this.router.navigate(['/login']);
    }

    let headers: HttpHeaders = new HttpHeaders({
      'Authorization': 'Basic '+sessionStorage.getItem('token')
    });

    let options = {headers: headers};
    return this.http.get<Game[]>(this.gamesUrl, options);
  }

  public save(game: Game){
    return this.http.post<Game>(this.gamesUrl, game);
  }
}
