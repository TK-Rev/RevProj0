import scala.io.AnsiColor.{CYAN,RESET,BOLD}

class Armor(d:String,n:String,c:String,h:Int,p:Int,a:Int,i:Int,w:Int) {
  var id:String = d //n.toLowerCase.filterNot(_.isWhitespace)

  val name:String = n
  val category:String = c
  // Categories:
  // Tank, Melee, Ranged, Magic, Healer
  val hp:Int = h
  val pwr:Int = p
  val acc:Int = a
  val int:Int = i
  val wis:Int = w


  def this(){
    this("examplearmor","Example Armor","Tank",75,7,5,3,3)
  }

  override def toString: String = {
    s"${CYAN}${BOLD}$name${RESET} - ($id)\n<Armor - $category>\n[HP:$hp | PWR:$pwr | ACC:$acc | INT:$int | WIS:$wis]"
  }

  def shortString: String ={
    s"${CYAN}${BOLD}$name${RESET} - $id - <$category>"
  }

  def shortNoID: String ={
    s"$CYAN$BOLD$name$RESET - <$category>"
  }

  def reID(wew:String): Unit ={
    id=wew
  }
}
