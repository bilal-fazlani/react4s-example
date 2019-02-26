package tutorial.webapp.components

import com.github.ahnfelt.react4s.{A, _}
import tutorial.webapp.actions._
import tutorial.webapp.models.Todo
import tutorial.webapp.state.AppCircuit

case class TodoItem(todo: P[Todo]) extends Component[AppAction] {
  override def render(get: Get): Node = {
    val item = get(todo)

    println(s"render: ${getClass.getSimpleName}, $item")

    val delete = E.div(
      A.id("delete-button"),
      A.onClick(_ => AppCircuit.dispatch(DeleteTodo(item))),
      E.img(A.src("images/delete.png"))
    )

    E.li(
      A.className(if (item.done) "done" else "active"),
      A.tabIndex("0"),
      A.onKeyUp(e =>
        e.key match {
          case "Enter" =>
            AppCircuit.dispatch(
              if (item.done) MarkAsUndone(item) else MarkAsDone(item))
          case _ =>
      }),
      A.onClick(
        _ =>
          AppCircuit.dispatch(
            if (item.done) MarkAsUndone(item) else MarkAsDone(item))),
      A.id("item"),
      E.span(Text(item.text)),
      delete
    )
  }
}
