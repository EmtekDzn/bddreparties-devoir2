package Exercice2.Bestiary

import Exercice2.{Attack, Position}
import Exercice2.BrutalWarlord

class BrutalWarlord(id: Int, position: Position) extends Mob (
    id,
    "Brutal Warlord",
    BrutalWarlord.HP,
    BrutalWarlord.MAXHP,
    BrutalWarlord.REGEN,
    BrutalWarlord.ARMOR,
    new Attack("+1 vicious flail", List(20, 15, 10), 5),
    new Attack("mwk throwing axe", List(19), 110),
    position
) {

}
    