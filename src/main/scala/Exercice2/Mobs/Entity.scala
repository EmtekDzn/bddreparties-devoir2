package Exercice2.Mobs

import Exercice2.{Attack, Position}

case class Entity(
                   //Entity specs
                   id: Int,
                   name: String,
                   hpMax: Int,
                   var hp: Int,
                   regen: Int,
                   armor: Int,
                   moveRange: Int,
                   flyingRange: Int,
                   team: String,


                  //Attacks
                   rangedAttack: Attack,
                   meleeAttack: Attack,

                   position: Position
                 ) {

    override def toString: String = "Id : " + id +
      "\nName : " + name +
      "\nHp : " + hp +
      "\nMax hp : " + hpMax +
      "\nRegeneration : " + regen +
      "\nArmor : " + armor +
      "\nRanged attack : " + rangedAttack +
      "\nMelee attack : " + meleeAttack +
      "\nPosition : x=" + position.x + ", y=" + position.y + ", z=" + position.z

//  def rangedAttack(cible:Entity, idCible:Int, diceSize:Int, diceTry:Int): Int ={
//    val random = scala.util.Random
//    //On vérifie que la distance entre la cible et l'entitée correspond aux Spec. d'attaque
//    val distCibleToThis = ((((cible.position.x - this.position.x)^2+(cible.position.y - this.position.y)^2)^(1/2))+(cible.position.z - this.position.z)^2)^(1/2);
//    //On init la var de calcul des dommages
//    var damages:Int = 0;
//    if (distCibleToThis < this.rangedAttack.minDist || distCibleToThis > this.rangedAttack.maxDist){
//      var dice20 = 1+random.nextInt(20);
//      if (dice20 == 20 || this.rangedAttack.accuracies(1) + dice20 > cible.armor){
//        for(i <- 0 to diceTry){
//          damages -= 1+random.nextInt(diceSize);
//        }
//      }else{
//        println ("L'attaque à distance à échoué ! Too much armor");
//      }
//    }else{
//      println ("L'attaque à distance à échoué !");
//    }
//    return damages;
//  }
//
//  def meleeAttack(cible:Entity, idCible:Int, diceSize:Int, diceTry:Int): Unit ={
//    val random = scala.util.Random
//    if (cible.position.isFlying == this.position.isFlying){
//      //On vérifie que la distance entre la cible et l'entitée correspond aux Spec. d'attaque
//      val distCibleToThis = ((((cible.position.x - this.position.x)^2+(cible.position.y - this.position.y)^2)^(1/2))+(cible.position.z - this.position.z)^2)^(1/2);
//      //On init la var de calcul des dommages
//      var damages:Int = 0;
//
//      if (this.position.isFlying == cible.position.isFlying && (distCibleToThis < this.meleeAttack.minDist || distCibleToThis > this.meleeAttack.maxDist)){
//        var dice20 = 1+random.nextInt(20);
//        if (dice20 == 20 || this.meleeAttack.accuracies(1) + dice20 > cible.armor){
//          for(i <- 0 to diceTry){
//            damages -= 1+random.nextInt(diceSize);
//          }
//        }else{
//          println ("L'attaque de melée à échoué ! Too much armor");
//        }
//      }else{
//        println ("L'attaque de melée à échoué !");
//      }
//      return damages;
//    }
//  }
//
//  def move(cible:Entity): Unit ={
//    var z:Double = 0
//
//    //dist who can be run by entity in one iteration
//    val range = if(this.position.isFlying){this.flyingRange}else{this.moveRange};
//
//    val theta = Math.atan2(cible.position.y-this.position.y, cible.position.x-this.position.x)
//    val x = min(abs(cible.position.x-this.position.x),range) * Math.cos(theta)
//    val y = min(abs(cible.position.y-this.position.y),range) * Math.sin(theta)
//
//    if(this.flyingRange > 0){
//      val thetaZ = Math.atan2(x-y, cible.position.z-this.position.z)
//      z = min(abs(cible.position.z-this.position.z),range) * Math.sin(thetaZ)
//    }
//
//    setPosition(x.toInt,y.toInt, z.toInt,if (z>0){true}else{false})
//  }

  //Must be use just after init of entity to set pos on grid
  def setPosition(x:Int, y:Int, z:Int, isFlying:Boolean): Unit = {
    this.position.x = x;
    this.position.y = y;
    this.position.z = z;
    this.position.isFlying = isFlying;
  }

  def think(): Unit ={
    //TODO strucuture de décision entre marher/attaquer/fuir/heal...

  }
}