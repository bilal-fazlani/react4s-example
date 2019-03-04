package tutorial.webapp.components.todo_item

import com.github.ahnfelt.react4s._
import tutorial.webapp.Actions.{DeleteTodo, MarkAsDone, MarkAsUndone}
import tutorial.webapp.state.{AppCircuit, SavedTodo}

case class SavedTodoItemRow(todo: P[SavedTodo]) extends Component[NoEmit] {
  override def render(get: Get): Node = {
    val item = get(todo)

    E.li(
      A.className(if (item.done) "done" else "active"),
      A.onClick(
        _ =>
          AppCircuit.dispatch(
            if (item.done) MarkAsUndone(item) else MarkAsDone(item))),
      A.id("item"),
      E.span(Text(item.text)),
      E.div(
        A.id("delete-button"),
        A.onClick(_ => AppCircuit.dispatch(DeleteTodo(item))),
        E.img(A.src("images/delete.png"))
      )
    )
  }
}
