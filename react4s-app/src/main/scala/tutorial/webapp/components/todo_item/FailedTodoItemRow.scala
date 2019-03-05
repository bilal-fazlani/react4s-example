package tutorial.webapp.components.todo_item

import com.github.ahnfelt.react4s.E.{apply => _, _}
import com.github.ahnfelt.react4s._
import tutorial.webapp.state.FailedTodo

case class FailedTodoItemRow(todo: P[FailedTodo]) extends Component[NoEmit] {
  override def render(get: Get): Node = {
    val item = get(todo)

    li(
      A.className("failed"),
      A.id("item"),
      E.span(Text(item.text)),
      div(A.className("failed-icon-container"),
          E.img(A.id("failed-icon"), A.src("images/failed.png")))
    )
  }
}
