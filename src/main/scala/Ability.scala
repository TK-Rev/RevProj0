import scala.io.AnsiColor.{MAGENTA,RESET,BOLD}

class Ability(i:String,n:String,t:String,d:String,c:Int,m:Int) {
  val id:String = i

  val name:String = n
  val category:String = t
  // Categories;
  // Generic, Melee, 1H Melee, 2H Melee, Shield, Ranged, Magic
  val desc:String = d
  val cd:Int = c
  val mp:Int = m

  def this(){
    this("exampleability","Example Ability","Generic","An example ability. It makes an example out of something.",5,35)
  }

  override def toString: String = {
    s"${MAGENTA}${BOLD}$name${RESET}\n<Ability - $category>\n[CD:$cd | MP:$mp]\n[$desc]"
  }
}
