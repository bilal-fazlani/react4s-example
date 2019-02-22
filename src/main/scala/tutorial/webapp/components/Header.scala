package tutorial.webapp.components

import com.github.ahnfelt.react4s._

case class Header() extends Component[NoEmit] {
  override def render(get: Get): Node = {
    E.div(A.id("header"), Text("Todo Application"))
  }
}
