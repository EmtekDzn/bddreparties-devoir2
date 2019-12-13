package Exercice2.Mobs

import Exercice2.{Attack, DoubleAxeFurySpecs, Position}

class DoubleAxeFury(id: Int, position: Position, team: String) extends Entity (
    id,
    "Double Axe Fury",
    DoubleAxeFurySpecs.HP,
    DoubleAxeFurySpecs.MAXHP,
    DoubleAxeFurySpecs.REGEN,
    DoubleAxeFurySpecs.ARMOR,
    DoubleAxeFurySpecs.MOVERANGE,
    DoubleAxeFurySpecs.FLYRANGE,
    team,
    Attack("+1 orc double axe", 0, 10, 0, 0, List(19, 14, 9, 20), true),
    Attack("mwk composite longbox", 0, 110, 0, 0, List(16, 11, 6), false),
    position
)