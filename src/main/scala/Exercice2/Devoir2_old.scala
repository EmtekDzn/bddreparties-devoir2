package Exercice2

import Exercice2.Bestiary._
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ListBuffer

object Fight extends App {
  //  Initialisation
  //  Initialisation
  val conf = new SparkConf()
    .setAppName("Crawler Ex1")
    .setMaster("local[*]")
  val sc = new SparkContext(conf)
  sc.setLogLevel("ERROR")
  val sqlContext= new org.apache.spark.sql.SQLContext(sc)



  case class Entity(
                   entity: Mob,
                   adjList: ListBuffer[Int]
                   )

  val random = scala.util.Random;
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

//  entityDF = entityList.toDF();
  for (i <- 0 to entityList.size-1){
    println("\n#############################################\n")
    print(entityList(i))
  }



  sc.stop()
}
