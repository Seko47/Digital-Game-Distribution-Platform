import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {GameListComponent} from "./components/game-list/game-list.component";
import {GameFormComponent} from "./components/game-form/game-form.component";
import {LoginComponent} from "./components/login/login.component";
import {RegisterComponent} from "./components/register/register.component";

const routes: Routes = [
  {path: "games", component: GameListComponent},
  {path: "games/add", component: GameFormComponent},
  {path: "login", component: LoginComponent},
  {path: "logout", component: LoginComponent},
  {path: "register", component: RegisterComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
