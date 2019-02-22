package tutorial.webapp.components

import com.github.ahnfelt.react4s.{A, _}
import tutorial.webapp.commands._
import tutorial.webapp.models.Todo

case class TodoItem(todo: P[Todo]) extends Component[AppCommand] {
  override def render(get: Get): Node = {
    val item = get(todo)

    val delete = E.button(
      A.id("delete-button"),
      A.onClick(_ => emit(DeleteTodo(item))),
      E.img(A.src("images/delete.png"))
    )

    E.li(
      A.className(if (item.done) "done" else "active"),
      A.onClick(_ =>
        emit(if (item.done) MarkAsUndone(item) else MarkAsDone(item))),
      A.id("item"),
      E.span(Text(item.text)),
      delete
    )
  }
}
