import java.sql.{Connection, DriverManager, Statement}
import scala.collection.mutable.ArrayBuffer
import scala.util.control.Breaks._

object DBAccess {
  val driver = "com.mysql.cj.jdbc.Driver"
  var connection:Connection = null
  var statement:Statement = null

  def getConnect(): Unit ={
    val url = "jdbc:mysql://localhost:3306/sdcproject"
    val username = "root"
    val password = "betaetadelota34"
    connection = DriverManager.getConnection(url,username,password)
    statement = connection.createStatement()
  }
  def dropConnect(): Unit ={
    connection.close()
  }

  def getArmors(): ArrayBuffer[Armor] ={
    getConnect()
    var arms:ArrayBuffer[Armor] = new ArrayBuffer[Armor]
    val results = statement.executeQuery("SELECT * FROM armors")

    while (results.next()) {
      var check = false
      breakable {
        SDCLoadout.dbAr.foreach(e => // goes through every id in the imported list to ensure we don't import duplicates.
          if (e.id == results.getString(1)) {
            check = true
            break
          }
        )
      }
      if (!check) {
        arms += new Armor(results.getString(1), results.getString(2), results.getString(3), results.getInt(4), results.getInt(5), results.getInt(6), results.getInt(7), results.getInt(8))
      }
    }
    dropConnect()
    println("Imported "+arms.length+" armors!")
    arms
  }
  def getArmorSpec(cate:String): ArrayBuffer[Armor] ={
    getConnect()
    var arms:ArrayBuffer[Armor] = new ArrayBuffer[Armor]
    val results = statement.executeQuery(s"SELECT * FROM armors WHERE Category=\'$cate\'") // NOT user input. This is my own passing.

    while (results.next()) {
      var check = false
      breakable {
        SDCLoadout.dbAr.foreach(e => // goes through every id in the imported list to ensure we don't import duplicates.
          if (e.id == results.getString(1)) {
            check = true
            break
          }
        )
      }
      if (!check) {
        arms += new Armor(results.getString(1), results.getString(2), results.getString(3), results.getInt(4), results.getInt(5), results.getInt(6), results.getInt(7), results.getInt(8))
      }
    }
    dropConnect()
    println("Imported "+arms.length+" armors!")
    arms
  }

  def getWeapons(): ArrayBuffer[Weapon] ={
    getConnect()
    var weps:ArrayBuffer[Weapon] = new ArrayBuffer[Weapon]
    val results = statement.executeQuery("SELECT * FROM weapons")

    while (results.next()) {
      var check = false
      breakable {
        SDCLoadout.dbWn.foreach(e =>
          if (e.id == results.getString(1)) {
            check = true
            break
          }
        )
      }
      if (!check) {
        weps += new Weapon(results.getString(1), results.getString(2), results.getString(3), results.getInt(4), results.getInt(5), results.getInt(6))
      }
    }
    dropConnect()
    println("Imported "+weps.length+" weapons!")
    weps
  }
  def getWeaponSpec(cate:String): ArrayBuffer[Weapon] ={
    getConnect()
    var weps:ArrayBuffer[Weapon] = new ArrayBuffer[Weapon]
    val results = statement.executeQuery(s"SELECT * FROM weapons WHERE category=\'$cate\'")

    while (results.next()) {
      var check = false
      breakable {
        SDCLoadout.dbWn.foreach(e =>
          if (e.id == results.getString(1)) {
            check = true
            break
          }
        )
      }
      if (!check) {
        weps += new Weapon(results.getString(1), results.getString(2), results.getString(3), results.getInt(4), results.getInt(5), results.getInt(6))
      }
    }
    dropConnect()
    println("Imported "+weps.length+" weapons!")
    weps
  }

  def getAbilities(): ArrayBuffer[Ability] ={
    getConnect()
    var abis:ArrayBuffer[Ability] = new ArrayBuffer[Ability]
    val results = statement.executeQuery("SELECT * FROM abilities")

    while (results.next()) {
      var check = false
      breakable {
        SDCLoadout.dbAb.foreach(e =>
          if (e.id == results.getString(1)) {
            check = true
            break
          }
        )
      }
      if (!check) {
        abis += new Ability(results.getString(1), results.getString(2), results.getString(3), results.getString(4), results.getDouble(5), results.getInt(6))
      }
    }
    dropConnect()
    println("Imported "+abis.length+" abilities!")
    abis
  }
  def getAbilitySpec(cate:String): ArrayBuffer[Ability] ={
    getConnect()
    var abis:ArrayBuffer[Ability] = new ArrayBuffer[Ability]
    val results = statement.executeQuery(s"SELECT * FROM abilities WHERE category=\'$cate\'")

    while (results.next()) {
      var check = false
      breakable {
        SDCLoadout.dbAb.foreach(e =>
          if (e.id == results.getString(1)) {
            check = true
            break
          }
        )
      }
      if (!check) {
        abis += new Ability(results.getString(1), results.getString(2), results.getString(3), results.getString(4), results.getDouble(5), results.getInt(6))
      }
    }
    dropConnect()
    println("Imported "+abis.length+" abilities!")
    abis
  }

  def getLoadouts(): ArrayBuffer[Loadout] ={
    getConnect()
    var loas:ArrayBuffer[Loadout] = new ArrayBuffer[Loadout]
    var results = statement.executeQuery("SELECT * FROM loadouts")

    while (results.next()) {
      var check = false
      breakable {
        SDCLoadout.dbLo.foreach(e =>
          if (e.id == results.getString(1)) {
            check = true
            break
          }
        )
      }
      if (!check) {
        loas += new Loadout(results.getString(1),results.getString(2),SDCLoadout.SelArmor(results.getString(3)),SDCLoadout.SelWeapon(results.getString(4)),SDCLoadout.SelWeapon(results.getString(5)),SDCLoadout.SelAbility(results.getString(6)),SDCLoadout.SelAbility(results.getString(7)))
      }
    }
    dropConnect()
    println("Imported "+loas.length+" loadouts!")
    loas
  }

  def uploArmor(arms:ArrayBuffer[Armor]): Unit ={
    getConnect()
    val tote = arms.length

    arms.foreach(e=>{
      val id = e.id
      val name = e.name
      val cate = e.category
      val hp = e.hp
      val pwr = e.pwr
      val acc = e.acc
      val int = e.int
      val wis = e.wis

      statement.executeUpdate(s"INSERT INTO armors(id,armName,category,hp,pwr,acc,inte,wis)\nVALUES (\'$id\',\'$name\',\'$cate\',$hp,$pwr,$acc,$int,$wis);")

      arms-=e
    })

    dropConnect()
    println(s"Exported $tote armors!")
    println("You can find your exported armors by re-importing!")
  }

  def uploWeapon(weps:ArrayBuffer[Weapon]): Unit ={
    getConnect()
    val tote = weps.length

    weps.foreach(e=>{
      val id = e.id
      val name = e.name
      val cate = e.category
      val dmg = e.dmg
      val mit = e.mit
      val pot = e.pot

      statement.executeUpdate(s"INSERT INTO weapons(id,wepName,category,dmg,mit,pot)\nVALUES (\'$id\',\'$name\',\'$cate\',$dmg,$mit,$pot);")

      weps-=e
    })

    dropConnect()
    println(s"Exported $tote weapons!")
    println("You can find your exported weapons by re-importing!")
  }

  def uploAbility(abis:ArrayBuffer[Ability]): Unit ={
    getConnect()
    val tote = abis.length

    abis.foreach(e=>{
      val id = e.id
      val name = e.name
      val cate = e.category
      val desco = e.desc
      val cd = e.cd
      val mp = e.mp

      statement.executeUpdate(s"INSERT INTO abilities(id,abiName,category,desco,cd,mp)\nVALUES(\'$id\',\'$name\',\'$cate\',\'$desco\',$cd,$mp);")

      abis-=e
    })

    dropConnect()
    println(s"Exported $tote abilities!")
    println("You can find your exported abilities by re-importing!")
  }

  def uploLoadout(loas:ArrayBuffer[Loadout]): Unit ={
    getConnect()
    val tote = loas.length

    loas.foreach(e=>{
      val id = e.id
      val name = e.name
      val arm = e.armor.id
      val wep1 = e.weapon1.id

      var wep2:String = null
      if(e.weapon2!=null) wep2 = "\'"+e.weapon2.id+"\'"
      var abi1:String = null
      if(e.ability1!=null) abi1 = "\'"+e.ability1.id+"\'"
      var abi2:String = null
      if(e.ability2!=null) abi2 = "\'"+e.ability2.id+"\'"

      statement.executeUpdate(s"INSERT INTO loadouts(id,loaName,armID,wepID,wep2ID,abiID,abi2ID)\nVALUES(\'$id\',\'$name\',\'$arm\',\'$wep1\',$wep2,$abi1,$abi2);")
      loas-=e
    })

    dropConnect()
    println(s"Exported $tote loadouts!")
    println("You can find your exported loadouts by re-importing!")
  }
}
