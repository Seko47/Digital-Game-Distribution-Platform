import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {Router} from "@angular/router";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Order} from "../models/order";

@Injectable({
  providedIn: 'root'
})
export class LibraryService {

  constructor(private router: Router, private http: HttpClient) { }

  public findAll(): Observable<Order[]> {
    let headers: HttpHeaders = new HttpHeaders({
      'Authorization': 'Basic ' + sessionStorage.getItem('token')
    });

    let options = {headers: headers};

    return this.http.get<Order[]>("http://localhost:8080/orders", options);
  }
}
