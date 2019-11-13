package Exercice2.Bestiary

import Exercice2.{Attack, Position}

class Mob (
    var id: Int = 0,
    var name: String = "",
    var hp: Int = 0,
    var maxHp: Int = 0,
    var regen: Int = 0,
    var armor: Int = 0,
    var rangedAttack: Attack = null,
    var meleeAttack: Attack = null,
    var position: Position = null
) {
    override def toString: String = "Id : " + id +
                                    "\nName : " + name +
                                    "\nHp : " + hp +
                                    "\nMax hp : " + maxHp +
                                    "\nRegeneration : " + regen +
                                    "\nArmor : " + armor +
                                    "\nRanged attack : " + rangedAttack +
                                    "\nMelee attack : " + meleeAttack +
                                    "\nPosition : x=" + position.x + ", y=" + position.y
}