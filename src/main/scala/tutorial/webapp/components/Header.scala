package tutorial.webapp.components

import com.github.ahnfelt.react4s._

case class Header() extends Component[NoEmit] {
  override def render(get: Get): Node = {
    E.h3(S.fontStyle.italic(),
         S.textShadow("grey 1px 1px 4px"),
         Text("Todo Application"))
  }
}
