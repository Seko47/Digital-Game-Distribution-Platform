import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {GameListComponent} from "./components/game-list/game-list.component";
import {LoginComponent} from "./components/login/login.component";
import {RegisterComponent} from "./components/register/register.component";
import {GameCreateComponent} from "./components/game-create/game-create.component";
import {GameEditComponent} from "./components/game-edit/game-edit.component";
import {GameDeleteComponent} from "./components/game-delete/game-delete.component";

const routes: Routes = [
  {path: "admin/games", component: GameListComponent},
  {path: "admin/games/add", component: GameCreateComponent},
  {path: "admin/games/edit/:id", component: GameEditComponent},
  {path: "login", component: LoginComponent},
  {path: "logout", component: LoginComponent},
  {path: "register", component: RegisterComponent},
  {path: "admin/games/delete/:id", component: GameDeleteComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
