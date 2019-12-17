package Exercice2

import Exercice2.Mobs._
import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}


object Fightv2 extends App {
  //  Initialisation
  //  Initialisation
  val conf = new SparkConf()
    .setAppName("Crawler Ex1")
    .setMaster("local[*]")
  val sc = new SparkContext(conf)
  sc.setLogLevel("ERROR")

  val spark = SparkSession.builder
    .master("local")
    .appName("Exercise 2")
    .getOrCreate()
  val sqlContext = spark.sqlContext
  import sqlContext.implicits._


  //Graph
  def addEntityToGraph(monster: Entity): Unit ={
    println("")
    sommet = sommet union Seq[(Int, Entity)]((entityId, monster)).toDS()
    entityId+=1
  }
  var iterationCount:Int = 0
  var entityId:Int = 0
  var gameIsWon:Boolean = false

  var sommet = Seq[(Int, Entity)]().toDS()
  var arrete = Seq[(Int, Seq[(Int, Int, Int, Int, Int)])]().toDS()

  println("Init mob in graph...")
  addEntityToGraph(new Solar(entityId, new Position(15,15,0,true),"Gentil"))
  addEntityToGraph(new OrcWorgRider(entityId, new Position(15,45,0,true),"Mechant"))
  println("Init mob in graph finish")

  sommet.show()
  while (!gameIsWon){
    iterationCount += 1;
    println("Tour n°"+iterationCount+"==================================")

    if (iterationCount>10){
      gameIsWon = true
    }
  }
  sc.stop()
}
