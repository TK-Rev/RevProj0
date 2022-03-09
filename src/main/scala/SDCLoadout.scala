import scala.collection.mutable.ArrayBuffer
import scala.io.StdIn.{readLine, readInt}

object SDCLoadout {
  var arms = new ArrayBuffer[Armor]
  var weps = new ArrayBuffer[Weapon]
  var abis = new ArrayBuffer[Ability]
//  var load = new ArrayBuffer[Loadout] work this out later

  def CreateArmor(): Unit ={
    var valid = false // Use this to ensure the user inputs correct values at specific spots.
    // really just for category lmao
    var notMake = false // just incase the user decides to not create the item

    println("Armor name:")
    val name = readLine
    println("Armor category:")
    var cate:String = null

    //<editor-fold desc="Loopy">
    do { // makes sure they actually type a proper category.
      println("[Tank | Melee | Ranged | Magic | Healer]")
      readLine.toLowerCase match {
        case "tank" => {
          cate = "Tank"
          valid = true
        }
        case "melee" => {
          cate = "Melee"
          valid = true
        }
        case "ranged" => {
          cate = "Ranged"
          valid = true
        }
        case "magic" => {
          cate = "Magic"
          valid = true
        }
        case "healer" => {
          cate = "Healer"
          valid = true
        }
        case _ => {
          println("Unclear input. Try again.")
        }
      }
    } while (!valid)
    //</editor-fold>

    println("Stat distribution;")
    println("HP | Hit Points?")
    val hp = readInt
    println("PWR | Power?")
    val pwr = readInt
    println("ACC | Accuracy?")
    val acc = readInt
    println("INT | Intellect?")
    val int = readInt
    println("WIS | Wisdom?")
    val wis = readInt
    var id = name.toLowerCase.filterNot(_.isWhitespace)
    var auto = 1 // for autosolving the id

    //<editor-fold desc="Check">
    do {
      var problem:Armor = null
      valid = true
      arms.foreach(e => {
        if (e.id == id) {
          valid = false // This means we have an issue with IDs, should rename.
          problem = e
        }
      }
      // also scan the already existing table from the database, which should have been imported into its own array.
      )

      if(!valid) {
        println("Problem encountered: We have a conflicting ID with an armor that already exists.")
        println("How would you like to address this problem?")
        println("[Custom | Auto | View | Cancel]")

        readLine.toLowerCase match {
          case "custom" => {
            println("Enter a custom ID. This will not include spaces, and be all lower case.")
            id = readLine.toLowerCase.filterNot(_.isWhitespace)
          }
          case "auto" => {
            id = name.toLowerCase.filterNot(_.isWhitespace) + auto
            auto += 1
          }
          case "view" => {
            println("Problem conflict: ")
            println(problem.toString)
          }
          case "cancel" => {
            valid = true
            notMake = true
          }
        }
      }
    } while (!valid)
    //</editor-fold>

    if (!notMake) {
      arms += new Armor(id,name,cate,hp,pwr,acc,int,wis)
      println("Armor created!")
    } else {
      println("Cancelled!")
    }
  }

  def main(args: Array[String]): Unit = {
    println("Welcome to the SDC database editor!")
    var quit = false

    while (!quit) {
      println("\nWhat would you like to do?\n[Create | View | Quit]")
      readLine.toLowerCase match {
        case "create" => Create()
        case "view" => View()
        case "quit" => quit = true
        case _ => println("Input unclear.")
      }
      // Add to this the ability to update or download the databases/tables
      // as well as the ability to read .csv (spreadsheets)
      // It'd probably be best to have a separate object that can do this, so all the files are handled elsewhere, and returned here.
    }
    println("Thanks for making! See you!")
  }

  def View(): Unit ={
    println("What would you like to view?\n[Armors | Weapons | Abilities | Back]")
    readLine.toLowerCase match {
      case "armor" | "arm" | "armors" => arms.foreach(e=>println(e.toString))
      case "weapon" | "wep" | "weap" | "weapons" => weps.foreach(e=>println(e.toString))
      case "ability" | "abilities" | "abils" | "abi" | "abi" => abis.foreach(e=>println(e.toString))
      case _ => {
        println("Input unclear.")
        View() // repeat, without making a while loop.
      }
    }
  }
  def Create(): Unit ={
    println("What would you like to create?\n[Armor | Back]")
    readLine.toLowerCase match {
      case "armor" => CreateArmor()
      case "back" => // do nothing
      case _ => {
        println("Input unclear.")
        Create() // go back into this until they give a good input. Didn't feel like making a while loop.
      }
    }
  }
}
