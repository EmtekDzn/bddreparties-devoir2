package Exercice2

case class Attack(
    name: String,
    minDist: Int,
    maxDist: Int,
    minDamages: Int,
    maxDamages: Int,
    accuracies: List[Int],
    isMelee: Boolean
) {

    def printAttackType: String = {
        if(isMelee) "Melee" else "Ranged"
    }

    override def toString: String = "Name : " + name +
                                    "Type : " + printAttackType +
                                    "\nMin damages : " + minDamages +
                                    "\nMax damages : " + maxDamages +
                                    "\n\tAccuracies : " + accuracies.mkString(", ") +
                                    "\n\tMin distance : " + minDist +
                                    "\nMax distance : " + maxDist
}