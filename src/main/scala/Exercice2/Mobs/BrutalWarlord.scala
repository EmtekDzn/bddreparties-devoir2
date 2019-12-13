package Exercice2.Mobs

import Exercice2.{Attack, BrutalWarlordSpecs, Position}

class BrutalWarlord(id: Int, position: Position, team: String) extends Entity (
    id,
    "Brutal Warlord",
    BrutalWarlordSpecs.HP,
    BrutalWarlordSpecs.MAXHP,
    BrutalWarlordSpecs.REGEN,
    BrutalWarlordSpecs.ARMOR,
    BrutalWarlordSpecs.MOVERANGE,
    BrutalWarlordSpecs.FLYRANGE,
    team,
    Attack("+1 vicious flail", 0, 10, 0, 0, List(20, 15, 10), true),
    Attack("mwk throwing axe", 0, 110, 0, 0, List(19), false),
    position
)