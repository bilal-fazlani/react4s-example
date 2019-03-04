package tutorial.webapp.components.todo_item

import com.github.ahnfelt.react4s.E.{apply => _, _}
import com.github.ahnfelt.react4s._
import tutorial.webapp.state.UnsavedTodo

case class UnsavedTodoItemRow(todo: P[UnsavedTodo]) extends Component[NoEmit] {
  override def render(get: Get): Node = {
    val item = get(todo)

    li(
      A.className("unsaved"),
      A.id("item"),
      E.span(Text(item.text)),
      div(
        A.className("loader-container"),
        E.div(A.className("loader"))
      )
    )
  }
}
