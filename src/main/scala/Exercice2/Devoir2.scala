package Exercice2

import Exercice2.Bestiary._
import breeze.linalg.min
import breeze.numerics.abs
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ListBuffer

object Fightv2 extends App {
  //  Initialisation
  //  Initialisation
  val conf = new SparkConf()
    .setAppName("Crawler Ex1")
    .setMaster("local[*]")
  val sc = new SparkContext(conf)
  sc.setLogLevel("ERROR")
  val sqlContext= new org.apache.spark.sql.SQLContext(sc)

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
      var rangeLeft = range;

      var theta = Math.atan2(cible.y-this.y, cible.x-this.x)
      if(this.flyingRange > 0){
        //calc z
      }
      val x = min(abs(cible.x-this.x),rangeLeft) * Math.cos(theta)
      val y = min(abs(cible.y-this.y),rangeLeft) * Math.sin(theta)
      if(this.flyingRange > 0){
        val z = min(abs(cible.z-this.z),rangeLeft) * Math.sin(theta)
      }else{
        val z = 0
      }



      //TODO calc max reachable place between this and target
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

  def Solar():Entity = {
    return new Entity("Solar",
      363,
        363,
        15,
        44,
        50,
        150,
        0,
      Map("move" -> "move",
        "melee" -> "dancing_greatsword",
        "ranged" -> "composite_longbow")
    )
  }
  def OrcBarbarian():Entity = {
    return new Entity("Double Axe Fury",
      Map("hpMax" -> 142,
        "hp" -> 142,
        "regen" -> 0,
        "armor" -> 17,
        "speed" -> 40,
        "flying" -> 0),
      Map("move" -> "move",
        "melee" -> "orc_double_axe",
        "ranged" -> "mwk_composite_longbow")
    )
  }
  def WorgRider():Entity = {
    return new Entity("Orc Worg Rider",
      Map("hpMax" -> 13,
        "hp" -> 13,
        "regen" -> 0,
        "armor" -> 18,
        "speed" -> 20,
        "flying" -> 0),
      Map("move" -> "move",
        "melee" -> "mwk_battleaxe",
        "ranged" -> "shortbow")
    )
  }
  def WarLord():Entity = {
    return new Entity("War Lord",
      Map("hpm" -> 141,
        "hp" -> 144,
        "regen" -> 0,
        "armor" -> 27,
        "speed" -> 30,
        "flying" -> 0),
      Map("move" -> "move",
        "melee" -> "vicious_flail",
        "ranged" -> "mwk_throwing_axe")
    )
  }


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
