import scala.collection.mutable.ArrayBuffer
import scala.io.StdIn.{readLine, readInt}
//import scala.util.Either

object SDCLoadout {
  var arms = new ArrayBuffer[Armor]
  var weps = new ArrayBuffer[Weapon]
  var abis = new ArrayBuffer[Ability]
  var loas = new ArrayBuffer[Loadout]
  var dbAr = new ArrayBuffer[Armor]
  var dbWn = new ArrayBuffer[Weapon]
  var dbAb = new ArrayBuffer[Ability]
  var dbLo = new ArrayBuffer[Loadout]

  def CreateArmor(): Unit ={
    var valid = false // Use this to ensure the user inputs correct values at specific spots.
    // really just for category lmao
    var notMake = false // just incase the user decides to not create the item
    var checkVal:Armor = null

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
    var auto = 0 // for autosolving the id

    //<editor-fold desc="Check">
    do {
      if (checkVal!=null&&auto==1) { // cause if auto isn't 1, then that means we're changing things, and don't need it.
        println("Problem encountered: We have a conflicting ID with another armor.")
        println("How would you like to solve this?")
        println("[Custom | Auto | View | Cancel]")

        readLine.toLowerCase match {
          case "custom" => {
            println("Enter a unique ID. This will not have spaces and will be all lower case.")
            id = readLine.toLowerCase.filterNot(_.isWhitespace)
          }
          case "auto" => {
            id = name.toLowerCase.filterNot(_.isWhitespace) + auto
            auto += 1
          }
          case "view" => {
            println("Conflict with;")
            println(checkVal.toString)
          }
          case "cancel" => {
            notMake = true
          }
        }
      }
      auto+=1
      checkVal = SelArmor(id)
    }while(checkVal!=null)
    //</editor-fold>

    if (!notMake) {
      arms += new Armor(id,name,cate,hp,pwr,acc,int,wis)
      println("Armor created!")
    } else {
      println("Cancelled!")
    }
  }
  def CreateWeapon(): Unit ={
    var valid = false
    var notMake = false
    var checkVal:Weapon = null

    println("Weapon name:")
    val name = readLine
    println("Weapon category:")
    var cate:String = null

    //<editor-fold desc="CategoryLoop">
    do {
      println("[1H Melee | 2H Melee | Shield | Ranged | Magic]")
      readLine.toLowerCase match {
        case "1h melee" => {
          cate = "1H Melee"
          valid = true
        }
        case "2h melee" => {
          cate = "2H Melee"
          valid = true
        }
        case "shield" => {
          cate = "Shield"
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
        case _ => println("Input unclear. Try again.")
      }
    } while (!valid)

    //</editor-fold>

    println("Stat distribution;")
    println("DMG | Damage?")
    val dmg = readInt
    println("MIT | Mitigation?")
    val mit = readInt
    println("POT | Potency?")
    val pot = readInt
    var id = name.toLowerCase.filterNot(_.isWhitespace)
    var auto = 0 // for autosolve

    //<editor-fold desc="VerifyID">
    do {
      if (checkVal!=null&&auto==1) { // cause if auto isn't 1, then that means we're changing things, and don't need it.
        println("Problem encountered: We have a conflicting ID with another weapon.")
        println("How would you like to solve this?")
        println("[Custom | Auto | View | Cancel]")

        readLine.toLowerCase match {
          case "custom" => {
            println("Enter a unique ID. This will not have spaces and will be all lower case.")
            id = readLine.toLowerCase.filterNot(_.isWhitespace)
          }
          case "auto" => {
            id = name.toLowerCase.filterNot(_.isWhitespace) + auto
            auto += 1
          }
          case "view" => {
            println("Conflict with;")
            println(checkVal.toString)
          }
          case "cancel" => {
            notMake = true
          }
        }
      }

      auto+=1

      checkVal = SelWeapon(id)
    }while(checkVal!=null)
    //</editor-fold>

    if (!notMake) {
      weps += new Weapon(id,name,cate,dmg,mit,pot)
      println("Weapon created!")
    } else {
      println("Cancelled!")
    }
  }
  def CreateAbility(): Unit ={
    var valid = false
    var notMake = false
    var checkVal:Ability = null

    println("Ability name:")
    val name = readLine
    println("Ability category:")
    var cate:String = null

    //<editor-fold desc="Category">
    do {
      println("[Generic | Melee | 1H Melee | 2H Melee | Shield | Ranged | Magic]")
      readLine.toLowerCase match {
        case "generic" => {
          cate = "Generic"
          valid = true
        }
        case "melee" => {
          cate = "Melee"
          valid = true
        }
        case "1h melee" => {
          cate = "1H Melee"
          valid = true
        }
        case "2h melee" => {
          cate = "2H Melee"
          valid = true
        }
        case "shield" => {
          cate = "Shield"
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
        case _ => println("Input unclear. Try again.")
      }
    } while (!valid)
    //</editor-fold>

    println("Description and stats;")
    println("Description of your ability?")
    val desc = readLine
    println("CD | Cooldown?")
    val cd = readInt
    println("MP | Mana Cost?")
    val mp = readInt
    var id = name.toLowerCase.filterNot(_.isWhitespace)
    var auto = 1

    //<editor-fold desc="VerifyID">
    do {
      if (checkVal!=null&&auto==1) { // cause if auto isn't 1, then that means we're changing things, and don't need it.
        println("Problem encountered: We have a conflicting ID with another ability.")
        println("How would you like to solve this?")
        println("[Custom | Auto | View | Cancel]")

        readLine.toLowerCase match {
          case "custom" => {
            println("Enter a unique ID. This will not have spaces and will be all lower case.")
            id = readLine.toLowerCase.filterNot(_.isWhitespace)
          }
          case "auto" => {
            id = name.toLowerCase.filterNot(_.isWhitespace) + auto
            auto += 1
          }
          case "view" => {
            println("Conflict with;")
            println(checkVal.toString)
          }
          case "cancel" => {
            notMake = true
          }
        }
      }
      auto+=1
      checkVal = SelAbility(id)
    }while(checkVal!=null)
    //</editor-fold>

    if (!notMake) {
      abis+=new Ability(id,name,cate,desc,cd,mp)
      println("Ability created!")
    } else {
      println("Cancelled!")
    }
  }
  def CreateLoadout(): Unit ={
    var valid = false
    var notMake = false

    println("Loadout name:")
    val name = readLine
    var arm:Armor = null

    //<editor-fold desc="Armor">
    do {
      println("Armor?")
      dbAr.foreach(e=>println(e.shortString))
      arms.foreach(e=>println(e.shortString))
      println("Enter their name or id, or '-1quit' to quit;")
      val typed = readLine
      typed.toLowerCase match {
        case "-1quit" => {
          valid = true
          notMake = true
        }
        case _ => {
          arm = SelArmor(typed)
          if (arm!=null) {
            valid = true
          }else{
            println("Couldn't find that armor.")
          }
        }
      }
    } while (!valid)
    //</editor-fold>

    if(!notMake)valid=false // reset, next one.
    var wep1:Weapon = null
    var wep2:Weapon = null

    //<editor-fold desc="Weapon">
    while (!valid) {
      println("Weapon?")
      dbWn.foreach(e=>println(e.shortString))
      weps.foreach(e=>println(e.shortString))
      println("Enter their name or id, '-1empty' for empty hand, or '-1quit' to quit;")
      val typed = readLine
      typed.toLowerCase match {
        case "-1quit" => {
          valid = true
          notMake = true
        }
        case "-1empty" => {
          if(wep1!=null){
            valid = true
          } else {
            println("You can't go without a mainhand weapon!")
          }
        }
        case _ => {
          if(wep1!=null) { // means they already grabbed first weapon.
            wep2 = SelWeapon(typed)
            if (wep2!=null) {
              if(wep2.category=="1H Melee"||wep2.category=="Shield") {
                // The only weapons that can go in your offhand.
                valid = true // since this means we're dual wielding
              } else {
                println("You can't equip two-handed weapons to your offhand!")
                wep2 = null
              }
            } else {
              println("Couldn't find that weapon.")
            }
          } else {
            wep1 = SelWeapon(typed)
            if (wep1!=null) {
              if(wep1.category=="2H Melee"||wep1.category=="Ranged"||wep1.category=="Magic") {
                // These categories are two handed.
                valid = true // since these are the only weapon they'll use.
              }
            } else {
              println("Couldn't find that weapon.")
            }
          }
        }
      }
    }
    //</editor-fold>

    if(!notMake)valid=false // reset, one more time.
    var abi1:Ability = null
    var abEmpty = false // refers only to abi1
    var abi2:Ability = null

    //<editor-fold desc="Abilities">
    while (!valid) {
      println("Ability?")
      dbAb.foreach(e=>println(e.shortString))
      abis.foreach(e=>println(e.shortString))
      println("Enter their name or id, '-1empty' for an empty ability slot, or '-1quit' to quit.")
      val typed = readLine
      typed.toLowerCase match {
        case "-1quit" => {
          valid = true
          notMake = true
        }
        case "-1empty" => {
          if (abi1!=null||abEmpty) {
            valid = true
            // means second slot is empty
          } else {
            abEmpty = true
            // means first slot is empty
          }
        }
        case _ => {
          if(abi1!=null||abEmpty) { // means they already grabbed first ability slot
            abi2 = SelAbility(typed)
            if(abi2!=null) { // contains used for "Melee" category ability with "1H Melee" or "2H Melee" category weapon.
              if(abi2.category=="Generic"||abi2.category==wep1.category||wep1.category.contains(abi2.category)) {
                // Means we fit either our first or second weapon slot
                valid = true // as we have both ability slots now.
              } else {
                if(wep2!=null) { // if we have a second weapon
                  if(abi2.category==wep2.category||wep2.category.contains(abi2.category)) {
                    //if they match our offhand
                    valid = true
                  } else {
                    println("Ability's category doesn't match your loadout! Try getting abilities with the same categories as your weapons!")
                    abi2 = null
                  }
                } else {
                  println("Ability's category doesn't match your loadout! Try getting abilities with the same categories as your weapons!")
                  abi2 = null
                }
              }
            } else {
              println("Couldn't find that ability!")
            }
          } else { // meaning this is their first ability slot
            abi1 = SelAbility(typed)
            if(abi1!=null) { //            this covers for if we use 'Melee' category v
              if(abi1.category=="Generic"||abi1.category==wep1.category||wep1.category.contains(abi1.category)) {
                // means we fit the slots.
                // and we don't actually do anything here cause now we just go back through
              } else {
                if(wep2!=null) { // if we have a second weapon
                  if(abi1.category==wep2.category||wep2.category.contains(abi1.category)) {
                    // matches offhand
                    // then it's all good and we can leave
                  } else {
                    println("Ability's category doesn't match your loadout! Try getting abilities with the same categories as your weapons!")
                    abi1 = null
                  }
                } else {
                  println("Ability's category doesn't match your loadout! Try getting abilities with the same categories as your weapons!")
                }
              }
            }else {
              println("Couldn't find that ability!")
            }
          }
        }
      }
    }
    //</editor-fold>

    var id = name.toLowerCase.filterNot(_.isWhitespace)
    var auto = 1

    //<editor-fold desc="VerifyID">
    do {
      var problem:Loadout = null
      valid = true
      loas.foreach(e => {
        if (e.id == id) {
          valid = false // This means we have an issue with IDs, should rename.
          problem = e
        }
      })
      dbLo.foreach(e => {
        if (e.id == id) {
          valid = false // PROBLEM WITH ACTUAL DB
          problem = e
        }
      })

      if(!valid&&auto==1) {
        println("Problem encountered: We have a conflicting ID with a loadout that already exists.")
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

    if(!notMake) {
      loas += new Loadout(id,name,arm,wep1,wep2,abi1,abi2)
      println("Loadout created!")
    } else {
      println("Cancelled!")
    }
  }

  def main(args: Array[String]): Unit = {
    println("Welcome to the SDC database editor!")
    var quit = false

    while (!quit) {
      println("\nWhat would you like to do?\n[Create | View | Import | Export | Clear | Quit]")
      readLine.toLowerCase match {
        case "create" => Create()
        case "view" => View()
        case "import" => Import()
        case "export" => Export()
        case "clear" => Clear()
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
    println("What would you like to view?\n[Armors | Weapons | Abilities | Loadouts | Back]")
    readLine.toLowerCase match {
      case "armor" | "armors" =>
        dbAr.foreach(e=>println(e.toString))
        arms.foreach(e=>println(e.toString))
      case "weapon" | "weapons" =>
        dbWn.foreach(e=>println(e.toString))
        weps.foreach(e=>println(e.toString))
      case "ability" | "abilities" =>
        dbAb.foreach(e=>println(e.toString))
        abis.foreach(e=>println(e.toString))
      case "loadout" | "loadouts" | "lo" =>
        dbLo.foreach(e=>println(e.toString+"\n"))
        loas.foreach(e=>println(e.toString+"\n"))
      case "back" => // do nothing.
      case _ => {
        println("Input unclear.")
        View() // repeat, without making a while loop.
      }
    }
  }
  def Create(): Unit ={
    println("What would you like to create?\n[Armor | Weapon | Ability | Loadout | Back]")
    readLine.toLowerCase match {
      case "armor" => CreateArmor()
      case "weapon" => CreateWeapon()
      case "ability" => CreateAbility()
      case "loadout" => CreateLoadout()
      case "back" => // do nothing
      case _ => {
        println("Input unclear.")
        Create() // go back into this until they give a good input. Didn't feel like making a while loop.
      }
    }
  }
  def Import(): Unit ={
    println("what would you like to import?\n[Armors | Weapons | Abilities | All | Back]")
    readLine.toLowerCase match {
      case "armor" | "armors" => ImportArmorSpec()
      case "weapon" | "weapons" => ImportWeaponSpec()
      case "ability" | "abilities" => ImportAbility()
      //case "loadout" | "loadouts" | "lo" => ImportLoadout()
      case "all" => {
        ImportArmor()
        ImportWeapon()
        ImportAbility()
        ImportLoadout()
      }
      case "back" => // do nothing
      case _ => {
        println("Input unclear.")
        Import() // do it again. No while loops.
      }
    }
  }
  def Export(): Unit ={
    println("This will first import from your database to check IDs, then export your local creations to your database.")
    println("Continue? [Y/N]")
    readLine.toLowerCase match {
      case "y" => { // TODO: just have a temporary import here that does it just to check (using SelDBArmor/etc) for IDs.
        println("Exporting armors...")
        DBAccess.uploArmor(arms)
      }
      case _ => {
        println("Cancelled!")
      }
    }
  }
  def Clear(): Unit ={
    println("This removes entries from your current, in-program, lists.")
    println("This does not touch your databases.")
    println("What would you like to clear?")
    println("[Imported | New | All | Cancel]")
    readLine.toLowerCase match {
      case "imported" => {
        println("This will also clear your new loadouts to prevent issues.\nIn the future this will be adjusted to include a proper check.\nContinue? [Y/N]")
        readLine.toLowerCase match {
          case "y" => {
            dbLo.clear()
            loas.clear()
            dbAb.clear()
            dbWn.clear()
            dbAr.clear()
          }
          case _ => {
            println("Cancelled.")
          }
        }
      }
      case "new" => {
        loas.clear()
        abis.clear()
        weps.clear()
        arms.clear()
      }
      case "all" | "both" => {
        dbLo.clear()
        loas.clear()
        dbAb.clear()
        abis.clear()
        dbWn.clear()
        weps.clear()
        dbAr.clear()
        arms.clear()
      }
      case "cancel" => // do nothing
      case _ => {
        println("Input unclear.")
        Clear()
      }
 /*   println("[Armor | Weapons | Abilities | Loadouts | Cancel]")
    readLine.toLowerCase match {
      case "armor" | "armors" => {
        println("This will also clear loadouts that use armors in question.")
        println("What would you like to clear?")
        println("Old armors are imported from your database, new armors were created by you this session.")
        println("[Old | New | Both | Cancel]")
      }
      case "weapons" | "weapon" => {

      }
      case "abilities" | "ability" => {

      }
      case "loadouts" | "loadout" | "lo" => {

      }
      case "cancel" => // do nothing, and leave.
      case _ => {
        println("Input unclear.")
        Clear()
      } */
    }
  }

  def SelArmor(inpu:String): Armor ={
    var cort:Armor = null

    for(i<-arms){
      if(i.id==inpu||i.name==inpu){
        cort = i
        return cort
      }
    }
    SelDBArmor(inpu)
  }
  def SelWeapon(inpu:String): Weapon ={
    var cort:Weapon = null

    for(i<-weps){
      if(i.id==inpu||i.name==inpu){
        cort = i
        return cort
      }
    }
    SelDBWeapon(inpu)
  }
  def SelAbility(inpu:String): Ability ={
    var cort:Ability = null

    for(i<-abis){
      if(i.id==inpu||i.name==inpu){
        cort = i
        return cort
      }
    }
    SelDBAbility(inpu)
  }

  def SelDBArmor(inpu:String): Armor ={
    var cort:Armor = null
    for(i<-dbAr){
      if(i.id==inpu||i.name==inpu){
        cort = i
        return cort
      }
    }
    null
  }
  def SelDBWeapon(inpu:String): Weapon ={
    var cort:Weapon = null
    for(i<-dbWn){
      if(i.id==inpu||i.name==inpu){
        cort = i
        return cort
      }
    }
    null
  }
  def SelDBAbility(inpu:String): Ability ={
    var cort:Ability = null
    for(i<-dbAb){
      if(i.id==inpu||i.name==inpu){
        cort = i
        return cort
      }
    }
    null
  }

  def ImportArmorSpec(): Unit ={
    println("What category of armors to import?")
    println("[Tank | Melee | Ranged | Magic | Healer | All | Back]")
    readLine.toLowerCase match {
      case "tank" =>
        println("Importing tank armor...")
        dbAr.appendAll(DBAccess.getArmorSpec("Tank"))
      case "melee" =>
        println("Importing melee armor...")
        dbAr.appendAll(DBAccess.getArmorSpec("Melee"))
      case "ranged" =>
        println("Importing ranged armor...")
        dbAr.appendAll(DBAccess.getArmorSpec("Ranged"))
      case "magic" =>
        println("Importing magic armor...")
        dbAr.appendAll(DBAccess.getArmorSpec("Magic"))
      case "healer" =>
        println("Importing healer armor...")
        dbAr.appendAll(DBAccess.getArmorSpec("Healer"))
      case "all" =>
        println("Importing all armor...")
        dbAr.appendAll(DBAccess.getArmors())
      case "back" => // do nothing, we're leaving.
      case _ =>
        println("Input unclear.")
        ImportArmorSpec()
    }
  }
  def ImportArmor(): Unit ={
    println("Importing armors, please wait...")
    val newSet = DBAccess.getArmors()
    dbAr.prependAll(newSet)
  }

  def ImportWeaponSpec(): Unit ={
    println("What category of weapons to import?")
    println("[1H Melee | 2H Melee | Shield | Ranged | Magic | All | Back]")
    readLine.toLowerCase match {
      case "1h melee" =>
        println("Importing 1H melee weapons...")
        dbWn.appendAll(DBAccess.getWeaponSpec("1H Melee"))
      case "2h melee" =>
        println("Importing 2H melee weapons...")
        dbWn.appendAll(DBAccess.getWeaponSpec("2H Melee"))
      case "shield" =>
        println("Importing shields...")
        dbWn.appendAll(DBAccess.getWeaponSpec("Shield"))
      case "ranged" =>
        println("Importing ranged weapons...")
        dbWn.appendAll(DBAccess.getWeaponSpec("Ranged"))
      case "magic" =>
        println("Importing magic weapons...")
        dbWn.appendAll(DBAccess.getWeaponSpec("Magic"))
      case "all" =>
        println("Importing all weapons...")
        dbWn.appendAll(DBAccess.getWeapons())
      case "back" => // do nothing
      case _ =>
        println("Input unclear.")
        ImportWeaponSpec()
    }
  }
  def ImportWeapon(): Unit ={
    println("Importing weapons, please wait...")
    val newSet = DBAccess.getWeapons()
    dbWn.prependAll(newSet)
    //wepIn += newSet.length
  }

  def ImportAbilitySpec(): Unit ={
    println("What category of abilities to import?")
    println("[Generic | Melee | 1H Melee | 2H Melee | Shield | Ranged | Magic | All | Back]")
    readLine.toLowerCase match {
      case "generic" =>
        println("Importing generic abilities...")
        dbAb.appendAll(DBAccess.getAbilitySpec("Generic"))
      case "melee" =>
        println("Importing melee abilities...")
        dbAb.appendAll(DBAccess.getAbilitySpec("Melee"))
      case "1h melee" =>
        println("Importing 1h melee abilities...")
        dbAb.appendAll(DBAccess.getAbilitySpec("1H Melee"))
      case "2h melee" =>
        println("Importing 2h melee abilities...")
        dbAb.appendAll(DBAccess.getAbilitySpec("2H Melee"))
      case "shield" =>
        println("Importing shield abilities...")
        dbAb.appendAll(DBAccess.getAbilitySpec("Shield"))
      case "ranged" =>
        println("Importing ranged abilities...")
        dbAb.appendAll(DBAccess.getAbilitySpec("Ranged"))
      case "magic" =>
        println("Importing magic abilities...")
        dbAb.appendAll(DBAccess.getAbilitySpec("Magic"))
      case "all" =>
        println("Importing all abilities...")
        dbAb.appendAll(DBAccess.getAbilities())
      case "back" => // do nothing
      case _ =>
        println("Input unclear.")
        ImportAbilitySpec()
    }
  }
  def ImportAbility(): Unit ={
    println("Importing abilities, please wait...")
    val newSet = DBAccess.getAbilities()
    dbAb.prependAll(newSet)
    //abiIn+=newSet.length
  }

  def ImportLoadout(): Unit ={
    println("Importing loadouts, please wait...")
    val newSet = DBAccess.getLoadouts()
    dbLo.prependAll(newSet)
    //loaIn+=newSet.length
    println("Imported "+newSet.length+" loadouts!")
  }

}
