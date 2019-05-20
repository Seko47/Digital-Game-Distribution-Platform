import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {GameListComponent} from "./components/game-list/game-list.component";
import {GameFormComponent} from "./components/game-form/game-form.component";
import {LoginComponent} from "./components/login/login.component";

const routes: Routes = [
  {path: "games", component: GameListComponent},
  {path: "addgame", component: GameFormComponent},
  {path: "login", component: LoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
