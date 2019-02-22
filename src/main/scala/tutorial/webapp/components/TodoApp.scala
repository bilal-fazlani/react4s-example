package tutorial.webapp.components

import com.github.ahnfelt.react4s._

case class TodoApp() extends Component[NoEmit] {
  val data = State(List.empty[String])

  override def render(get: Get): Node = {
    E.div(
      Component(Header),
      Component(Toolbar).withHandler(newEntry => {
        data.set((newEntry :: get(data)).reverse)
      }),
      Component(TodoList, get(data))
    )
  }
}
