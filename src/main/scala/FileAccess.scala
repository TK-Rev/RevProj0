import scala.io.Source._

object FileAccess {
  def grabFrom(inpu:String): Unit = {
    var file = inpu

    if(!file.endsWith(".csv")){
      file+=".csv"
    }

    println("Reading from "+file+"...")

    val bufSource = fromFile("src/main/scala/files/"+file)
    for (line <- bufSource.getLines){
      val cols = line.split(",").map(_.trim)
      // How to set this up?
      // Type, ID, Name, Category, HP/DMG/Desco/ARM, PWR/MIT/CD/WEP1, ACC/POT/MP/WEP2, INT/ABI1, WIS/ABI2

      cols(0).toLowerCase() match {
        case "armor" => appendArmor(cols)
        case "weapon" => appendWeapon(cols)
        case "ability" => appendAbility(cols)
       // case "loadout" => appendLoadout(cols)
        // loadout on hold because they are messy.
        case _ => // do nothing
      }
    }
    bufSource.close()
  }

  def appendArmor(set:Array[String]): Unit ={
    val newArm = new Armor(set(1),set(2),set(3),set(4).toInt,set(5).toInt,set(6).toInt,set(7).toInt,set(8).toInt)

    println(s"Found armor; ${set(1)}.")

    SDCLoadout.arms+=newArm
  }

  def appendWeapon(set:Array[String]):Unit={
    val newWep = new Weapon(set(1),set(2),set(3),set(4).toInt,set(5).toInt,set(6).toInt)

    println(s"Found weapon; ${set(1)}.")

    SDCLoadout.weps+=newWep
  }

  def appendAbility(set:Array[String]):Unit={
    val newAbi = new Ability(set(1),set(2),set(3),set(4),set(5).toDouble,set(6).toInt)

    println(s"Found ability; ${set(1)}.")

    SDCLoadout.abis+=newAbi
  }
}
