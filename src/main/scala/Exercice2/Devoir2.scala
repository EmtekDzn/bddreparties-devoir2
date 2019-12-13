package Exercice2
import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

import scala.math._



object Fightv2 extends App {
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

  case class Entity(
                   //Entity spec
                   name:String,
                   hpMax: Int,
                   var hp: Int,
                   regen: Int,
                   armor: Int,
                   moveRange: Int,
                   flyingRange: Int,

                   //Position Spec
                   var x:Int,
                   var y:Int,
                   var z:Int,
                   var isFlying: Boolean,
                   team:String,

                   //Skills spec
                   rangedAttackName: String,
                   rangedAttackMinDist: Int,
                   rangedAttackMaxDist: Int,
                   rangedAttackMinDamage: Int,
                   rangedAttackMaxDamage: Int,
                   rangedAttackAccuracies: Int,

                   meleeAttackName: String,
                   meleeAttackMinDist: Int,
                   meleeAttackMaxDist: Int,
                   meleeAttackMinDamage: Int,
                   meleeAttackMaxDamage: Int,
                   meleeAttackAccuracies: Int

                   //Graph

                   ){
    //Methode for entity
    //TODO replace "=> Unit" by return type

    def rangedAttack(cible:Entity, idCible:Int, diceSize:Int, diceTry:Int): Int ={
      //On vérifie que la distance entre la cible et l'entitée correspond aux Spec. d'attaque
      val distCibleToThis = ((((cible.x - this.x)^2+(cible.y - this.y)^2)^(1/2))+(cible.z - this.z)^2)^(1/2);
      //On init la var de calcul des dommages
      var damages:Int = 0;
      if (distCibleToThis < this.rangedAttackMinDist || distCibleToThis > this.rangedAttackMaxDist){
        var dice20 = 1+random.nextInt(20);
        if (dice20 == 20 || this.rangedAttackAccuracies+dice20 > cible.armor){
          for(i <- 0 to diceTry){
            damages -= 1+random.nextInt(diceSize);
          }
        }else{
          println ("L'attaque à distance à échoué ! Too much armor");
        }
      }else{
        println ("L'attaque à distance à échoué !");
      }
      return damages;
    }

    def meleeAttack(cible:Entity, idCible:Int, diceSize:Int, diceTry:Int): Unit ={
      if (cible.isFlying == this.isFlying){
        //On vérifie que la distance entre la cible et l'entitée correspond aux Spec. d'attaque
        val distCibleToThis = ((((cible.x - this.x)^2+(cible.y - this.y)^2)^(1/2))+(cible.z - this.z)^2)^(1/2);
        //On init la var de calcul des dommages
        var damages:Int = 0;

        if (this.isFlying == cible.isFlying && (distCibleToThis < this.meleeAttackMinDist || distCibleToThis > this.meleeAttackMaxDist)){
          var dice20 = 1+random.nextInt(20);
          if (dice20 == 20 || this.meleeAttackAccuracies+dice20 > cible.armor){
            for(i <- 0 to diceTry){
              damages -= 1+random.nextInt(diceSize);
            }
          }else{
            println ("L'attaque de melée à échoué ! Too much armor");
          }
        }else{
          println ("L'attaque de melée à échoué !");
        }
        return damages;
    }
    }
    //Must be use when entity decide to move
    def move(cible:Entity): Unit ={
      //dist who can be run by entity in one iteration
      val range = if(isFlying){this.flyingRange}else{this.moveRange};

      val theta = Math.atan2(cible.y-this.y, cible.x-this.x)
      val x = min(abs(cible.x-this.x),range) * Math.cos(theta)
      val y = min(abs(cible.y-this.y),range) * Math.sin(theta)
      if(this.flyingRange > 0){
        val thetaZ = Math.atan2(x-y, cible.z-this.z)
        val z = min(abs(cible.z-this.z),range) * Math.sin(thetaZ)
      }else{
        val z = 0
      }
      setPosition(x.toInt,y.toInt,z.toInt,if (z>0){true}else{false})
    }

    //Must be use just after init of entity to set pos on grid
    def setPosition(x:Int, y:Int, z:Int, isFlying:Boolean): Unit = {
      this.x = x;
      this.y = y;
      this.z = z;
      this.isFlying = isFlying;
    }

    def think(): Unit ={
      //TODO strucuture de décision entre marher/attaquer/fuir/heal...

    }
  }


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
