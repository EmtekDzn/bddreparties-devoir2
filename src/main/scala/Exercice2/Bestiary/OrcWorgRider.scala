package Exercice2.Bestiary

import Exercice2.{Attack, Position}
import Exercice2.OrcWorgRider

class OrcWorgRider(id: Int, position: Position) extends Mob (
    id,
    "Orc Worg Rider",
    OrcWorgRider.HP,
    OrcWorgRider.MAXHP,
    OrcWorgRider.REGEN,
    OrcWorgRider.ARMOR,
    new Attack("mwk battleaxe", List(6), 5),
    new Attack("shortbow", List(4), 110),
    position
  )
{

}
