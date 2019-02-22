package tutorial.webapp.components

import com.github.ahnfelt.react4s._

case class TodoList(list: P[List[String]]) extends Component[NoEmit] {
  override def render(get: Get): Node = {
    E.div(
      E.ul(
        Tags(get(list).map(t => E.li(Text(t))))
      ))
  }
}
