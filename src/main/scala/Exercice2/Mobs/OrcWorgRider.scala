package Exercice2.Mobs

import Exercice2.{Attack, OrcWorgRiderSpecs, Position}

class OrcWorgRider(id: Int, position: Position, team: String) extends Entity (
    id,
    "Orc Worg Rider",
    OrcWorgRiderSpecs.HP,
    OrcWorgRiderSpecs.MAXHP,
    OrcWorgRiderSpecs.REGEN,
    OrcWorgRiderSpecs.ARMOR,
    OrcWorgRiderSpecs.MOVERANGE,
    OrcWorgRiderSpecs.FLYRANGE,
    team,
    Attack("mwk battleaxe", 0, 10, 0, 0, List(6), true),
    Attack("shortbow", 0, 110, 0, 0, List(4), false),
    position
)