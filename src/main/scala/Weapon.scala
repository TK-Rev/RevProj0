import scala.io.AnsiColor.{YELLOW,RESET,BOLD}

class Weapon(i:String,n:String,c:String,d:Int,m:Int,p:Int) {
  val id:String = i

  val name:String = n
  val category:String = c
  // Categories;
  // 1H Melee, 2H Melee, Shield, Ranged, Magic
  val dmg:Int = d
  val mit:Int = m
  val pot:Int = p

  def this() {
    this("examplesword","Example Sword","1H Melee",4,0,3)
  }

  override def toString: String = {
    s"${YELLOW}${BOLD}$name${RESET}\n<Weapon - $category>\n[DMG:$dmg | MIT:$mit | POT:$pot]"
  }
}
