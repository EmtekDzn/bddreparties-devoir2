package Exercice2

import Exercice2.Mobs._
import breeze.linalg.min
import breeze.numerics.abs
import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

import scala.math._


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

  val random = scala.util.Random;

    //Methode for entity
    //TODO replace "=> Unit" by return type

  var entityList = ListBuffer[Entity]()

  entityList += new Entity(new Solar(1, new Position(0,10,0)), ListBuffer[Int](2,3,4,5,6,7,8,9,10,11,12,13,14,15));
  for (i <- 1 to 9){
    entityList += new Entity(new OrcWorgRider(i+1, new Position(100+random.nextInt(20), random.nextInt(20), 0)), ListBuffer[Int](1));
//    entityList += new OrcWorgRider(i+1, new Position(100+random.nextInt(20), random.nextInt(20), 0));
  }

  for (i <- 1 to 4){
    entityList += new Entity(new DoubleAxeFury(i+1, new Position(115, 8+i, 0)), ListBuffer[Int](1));
//    entityList += new DoubleAxeFury(i+10, new Position(115, 8+i, 0));
  }
  entityList += new Entity(new BrutalWarlord(15, new Position(120, 10, 0)), ListBuffer[Int](1));
//  entityList += new BrutalWarlord(15, new Position(120, 10, 0));

  //Graph
  def addEntityToGraph(monster: Entity): Unit ={
    sommet = sommet union Seq[(Int, Entity)]((entityId, monster)).toDS()
    entityId+=1
  }
  var iterationCount:Int = 0
  var entityId:Int = 0
  var gameIsWon:Boolean = false

  var sommet = Seq[(Int, Entity)]().toDS()
  var arrete = Seq[(Int, Seq[(Int, Int, Int, Int, Int)])]().toDS()

  sc.stop()
}
