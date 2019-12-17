package Exercice2

import org.apache.spark.sql.Encoder


case class Position(
    var x: Int,
    var y: Int,
    var z: Int,
    var isFlying: Boolean
) {
    override def toString: String = "Position : x: " + x + ", y: " + y + ", z: " + z + ", flies: " + isFlying

}

