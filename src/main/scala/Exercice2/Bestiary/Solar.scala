package Exercice2.Bestiary

import Exercice2.{Attack, Position}
import Exercice2.Solar

class Solar(id: Int, position: Position) extends Mob (
    id,
    "Solar",
    Solar.HP,
    Solar.MAXHP,
    Solar.REGEN,
    Solar.ARMOR,
    new Attack("+5 dancing greatsword", List(35, 30, 25, 20), 5),
    new Attack("+5 composite longbow", List(31, 26, 21, 16), 110),
    position
) {

}
    