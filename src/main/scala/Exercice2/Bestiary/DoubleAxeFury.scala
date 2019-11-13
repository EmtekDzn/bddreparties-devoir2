package Exercice2.Bestiary

import Exercice2.{Attack, Position}
import Exercice2.DoubleAxeFury

class DoubleAxeFury(id: Int, position: Position) extends Mob (
    id,
    "Double Axe Fury",
    DoubleAxeFury.HP,
    DoubleAxeFury.MAXHP,
    DoubleAxeFury.REGEN,
    DoubleAxeFury.ARMOR,
    new Attack("+1 orc double axe", List(19, 14, 9, 20), 5),
    new Attack("mwk composite longbox", List(16, 11, 6), 110),
    position
) {

}
    