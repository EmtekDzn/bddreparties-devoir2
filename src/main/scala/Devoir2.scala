import org.apache.spark.{SparkConf, SparkContext}

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
