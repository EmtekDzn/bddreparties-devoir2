package Exercice2.Mobs

import Exercice2.{Attack, Position, SolarSpecs}

class Solar(id: Int, position: Position, team: String) extends Entity (
    id,
    "Solar",
    SolarSpecs.MAXHP,
    SolarSpecs.HP,
    SolarSpecs.REGEN,
    SolarSpecs.ARMOR,
    SolarSpecs.MOVERANGE,
    SolarSpecs.FLYRANGE,
    team,
    Attack("+5 dancing greatsword", 0, 10, 0, 0, List(35, 30, 25, 20), true),
    Attack("+5 composite longbow", 0, 110, 0, 0, List(31, 26, 21, 16), false),
    position
)