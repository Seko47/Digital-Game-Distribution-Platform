import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {GameListComponent} from './components/game-list/game-list.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {GameService} from "./services/game.service";
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { GameCreateComponent } from './components/game-create/game-create.component';
import { GameEditComponent } from './components/game-edit/game-edit.component';
import { GameDeleteComponent } from './components/game-delete/game-delete.component';
import { GameShopComponent } from './components/game-shop/game-shop.component';
import { GameDetailsComponent } from './components/game-details/game-details.component';

@NgModule({
  declarations: [
    AppComponent,
    GameListComponent,
    LoginComponent,
    RegisterComponent,
    GameCreateComponent,
    GameEditComponent,
    GameDeleteComponent,
    GameShopComponent,
    GameDetailsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [GameService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
