import {Game} from "./game";
import {User} from "./user";

export class Order {
  id: string;
  game: Game;
  user: User;
  orderDate: string;
}
