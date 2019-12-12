package Exercice2

class Position(
    var x: Int = 0,
    var y: Int = 0,
    var z: Int = 0
) {

    override def toString: String = "Position : x=" + x + ", y=" + y + ", z=" + z
}