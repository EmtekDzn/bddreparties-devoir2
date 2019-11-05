import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ListBuffer
import scala.io.Source

object Crawler extends  App
{
  case class Creature(

                     )
  def splitRegex(value : String) : String = {
    val tmp = value.split("(<(|\\/)(b|p)>|;)")

    if(tmp.size >= 2)
      return tmp{2}
    else{
      return null
    }
  }

  //  Initialisation
  val conf = new SparkConf()
    .setAppName("Crawler Ex1")
    .setMaster("local[*]")
  val sc = new SparkContext(conf)
  sc.setLogLevel("ERROR")
  val sqlContext= new org.apache.spark.sql.SQLContext(sc)

  //  Initialisation basic var
  var spellList = ListBuffer[Creature]()
  var break = false


  // Initialisation Regex
  val creaturePattern = "<li>(.*?)<\\/li>".r


  val html = Source.fromURL("http://legacy.aonprd.com/bestiary/monsterIndex.html")
  var tmp = html.mkString
  tmp = creaturePattern.findAllIn(tmp).mkString("").toLowerCase()

  println("Début du parsing des sortilèges")
  do {


    if( tmp != ""){

    }

  }while(!break)

}

object Fight extends App{
  //  Initialisation
  val conf = new SparkConf()
    .setAppName("Crawler Ex1")
    .setMaster("local[*]")
  val sc = new SparkContext(conf)
  sc.setLogLevel("ERROR")
  val sqlContext= new org.apache.spark.sql.SQLContext(sc)

  case class Creature(
//                       self.id = 0,
//                       self.name = "",
//                       self.hpmax = 0,
//                       self.hp = 0,
//                       self.armor = 0,
//                       self.position = Position(),
//                       self.team = "",
//                       self.regeneration = 0,
//                       self.speeds = None,
//                       self.melee = None,
//                       self.ranged =  None,
//                       self.special = None,
//                       self.maxTargets = 1,
//                       self.targets = initSection(),
//                       self.hurtDuringRound = False
                     )
}
