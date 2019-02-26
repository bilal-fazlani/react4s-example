package tutorial.webapp.components

import com.github.ahnfelt.react4s._

case class TodoApp() extends Component[NoEmit] {

  override def render(get: Get): Node = {
    println(s"render: ${getClass.getSimpleName}")
    E.div(
      Component(Header),
      Component(Toolbar),
      Component(TodoList),
      Component(Footer)
    )
  }
}
