package Exercice2

class Attack(
    var name: String,
    //var minDamages: Int,
    //var maxDamages: Int,
    var accuracies: List[Int],
    var minDistance: Int
    //var maxDistance: Int
) {
    override def toString: String = "Name : " + name +
                                    //"\nMin damages : " + minDamages +
                                    //"\nMax damages : " + maxDamages +
                                    "\n\tAccuracies : " + accuracies.mkString(", ") +
                                    "\n\tMin distance : " + minDistance //+
                                    //"\nMax distance : " + maxDistance

}