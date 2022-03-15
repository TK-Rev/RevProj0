import scala.io.AnsiColor.{GREEN,RESET,BOLD}

class Loadout(i:String,n:String,a:Armor,w1:Weapon,w2:Weapon,a1:Ability,a2:Ability) {
  // work this out later.
  var id:String = i
  val name:String = n
  val armor:Armor = a
  val weapon1:Weapon = w1
  val weapon2:Weapon = w2
  val ability1:Ability = a1
  val ability2:Ability = a2

  override def toString: String = {
    var outpo:String = s"${GREEN}${BOLD}$name${RESET} - ($id)\n"+armor.shortNoID+"\n"+weapon1.shortNoID+"\n"

    if(weapon2!=null) outpo+=weapon2.shortNoID+"\n"
    if(ability1!=null) outpo+=ability1.shortNoID+"\n"
    if(ability2!=null) outpo+=ability2.shortNoID+"\n"

    outpo
  }

  def reID(inpu:String): Unit ={
    id=inpu
  }

  def shortString: String ={
    s"$GREEN$BOLD$name$RESET - ($id)"
  }
}
