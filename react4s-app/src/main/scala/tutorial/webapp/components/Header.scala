package tutorial.webapp.components

import com.github.ahnfelt.react4s._

case class Header() extends Component[NoEmit] {
  override def render(get: Get): Node = {
    println(s"render: ${getClass.getSimpleName}")
    E.div(A.id("header"), Text("Todo Application"))
  }
}
