import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ListBuffer
import scala.io.Source

object crawler extends  App
{
//  Déclaration
  case class Spell(
                    name: String,
                    description: String,
                    id: Int,
                    school: String,
                    level: String,
                    casting_time: String,
                    components: String,
                    range: String,
                    effect: String,
                    duration: String,
                    saving_throw: String,
                    spell_resistance: String
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
  import sqlContext.implicits._

//  Initialisation basic var
  var spellList = ListBuffer[Spell]()
  var id = 1
  var break = false
  var empty = 0
  var component = 0

//  Pattern List to get data from page
  val spellPattern =("<!-- START Spell -->[\\w \\W]+<!-- END Spell -->").r
  val pPattern =("<p[^>]*>(.*?)<\\/p>").r
  val namePattern = ("<div class='heading'><p>(.*?)<\\/p><\\/div>").r
  val descPattern = ("<div class='spdesc'><p>(.*?)<\\/p><\\/div>").r
  val idPattern = ("<div class='spdesc'><p>(.*?)<\\/p><\\/div>").r
  val schoolPattern = ("<p class='spdet'><b>school<\\/b>(.*?);").r
  val levelPattern = ("<b>level<\\/b> (.*?)<\\/p>").r
  val castingPattern = ("<p class='spdet'><b>casting time<\\/b>(.*?)<\\/p>").r
  val componentPattern = ("<p class='spdet'><b>components<\\/b>(.*?)<\\/p>").r
  val rangePattern = ("<p class='spdet'><b>range<\\/b>(.*?)<\\/p>").r
  val effectPattern = ("<p class='spdet'><b>effect<\\/b>(.*?)<\\/p>").r
  val durationPattern = ("<p class='spdet'><b>duration<\\/b>(.*?)<\\/p>").r
  val savingPattern = ("<p class='spdet'><b>saving throw<\\/b>(.*?);").r
  val resistancePattern = ("<b>spell resistance<\\/b>(.*?)<\\/p>").r

//  Get and process data
  println("Début du parsing des sortilèges")
  do {
    val html = Source.fromURL("http://www.dxcontent.com/SDB_SpellBlock.asp?SDBID="+id)
    var tmp = html.mkString
    tmp = spellPattern.findAllIn(tmp).mkString("").toLowerCase()

//    If we found a spell on page we process it
    if (tmp != "") {
      empty = 0
      spellList += Spell(
        namePattern.findAllIn(tmp).mkString("").split("<(|\\/)p>"){1},
        descPattern.findAllIn(tmp).mkString("").split("<(|\\/)p>"){1},
        id,
        splitRegex(schoolPattern.findAllIn(tmp).mkString("")),
        splitRegex(levelPattern.findAllIn(tmp).mkString("")),
        splitRegex(castingPattern.findAllIn(tmp).mkString("")),
        splitRegex(componentPattern.findAllIn(tmp).mkString("")),
        splitRegex(rangePattern.findAllIn(tmp).mkString("")),
        splitRegex(effectPattern.findAllIn(tmp).mkString("")),
        splitRegex(durationPattern.findAllIn(tmp).mkString("")),
        splitRegex(savingPattern.findAllIn(tmp).mkString("")),
        splitRegex(resistancePattern.findAllIn(tmp).mkString(""))
      )
//      if(id == 50) component = splitRegex(componentPattern.findAllIn(tmp).mkString(""))
    }
    else{
      empty+=1
      if (empty > 2){
        break=true
      }
    }

    if (id%100 == 1) println(id)
    id += 1
  }
  while (!break )
  println("Parsing Terminé !")
  println("Nombre de sortilège: "+spellList.size)

  val spellDataFrames = spellList.toDF()

  println("Filter Methode on Dataframe:")
  val filter = spellDataFrames.filter(
    ($"level".contains("wizard 1") ||
      $"level".contains("wizard 2") ||
      $"level".contains("wizard 3") ||
      $"level".contains("wizard 4"))
      && $"components" === " v"
  )
  filter.show()

  println("\nSQL Methode on Dataframe:")
  spellDataFrames.createOrReplaceTempView("spellListe")
  val sqlFilter = sqlContext.sql("SELECT * FROM spellListe " +
    " WHERE (level like \"%wizard 4%\"" +
    " OR level like \"%wizard 3%\"" +
    " OR level like \"%wizard 2%\"" +
    " OR level like \"%wizard 1%\")" +
    " AND components LIKE \" v\"")
  sqlFilter.show()

  val cpt = filter.count()
  println(cpt+" Wizard found by filter methode")
}

object Page extends App
{
  //  Initialisation
  val conf = new SparkConf()
    .setAppName("Page Rank Ex2")
    .setMaster("local[*]")
  val sc = new SparkContext(conf)
  sc.setLogLevel("ERROR")

  case class Sommet(var pagerank : Double = 1.0, var adjLists : Array[String] = Array())

  val data = Array(
    ("A", Sommet(1.0, Array("B","C"))),
    ("B", Sommet(1.0, Array("C"))),
    ("C", Sommet(1.0, Array("A"))),
    ("D", Sommet(1.0, Array("C")))
  )
  var datas = sc.makeRDD(data)

  val dumpingFactor = 0.85
  var n=20 //loop limit

  do{
    println("\nIteration n°"+n)
    val msg = datas
      .flatMap{case (_, sommet) => sommet.adjLists.map(adj => (adj, sommet.pagerank / sommet.adjLists.size))}
      .reduceByKey((a, b) => a+b)

//    print("msg")
//    msg.foreach(e => println("\t"+e))

    datas = datas.leftOuterJoin(msg).map(el => {
      el
      var pageranks = 0.0
      if (el._2._2.isDefined){
        pageranks = el._2._2.get
      }
      el
      el._2._1.pagerank = (1 - dumpingFactor) + dumpingFactor * pageranks
      (el._1, el._2._1)
    })

    println("Datas:")
    datas.foreach(e => println("\tSommet: "+e._1+" PageRank: "+e._2.pagerank))

    n-=1
  }while(n>0)
}