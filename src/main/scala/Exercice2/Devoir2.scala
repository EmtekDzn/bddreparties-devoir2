package Exercice2

import org.apache.spark.{SparkConf, SparkContext}

import Bestiary._

object Fight extends App {
  //  Initialisation
  val conf = new SparkConf()
    .setAppName("Crawler Ex1")
    .setMaster("local[*]")
  val sc = new SparkContext(conf)
  sc.setLogLevel("ERROR")
  val sqlContext = new org.apache.spark.sql.SQLContext(sc)

  var solar = new Solar(1, new Position())
  var brutalWarlord = new BrutalWarlord(2, new Position(1, 1))
  var doubleAxeFury = new DoubleAxeFury(3, new Position(2, 2))
  var orcWorgRider = new OrcWorgRider(4, new Position(3, 3))

  println(solar+"\n-------------")  
  println(brutalWarlord+"\n-------------")
  println(doubleAxeFury+"\n-------------")
  println(orcWorgRider+"\n-------------")

  sc.stop()
}
