package tutorial.webapp.components

import com.github.ahnfelt.react4s.{A, _}
import tutorial.webapp.commands._
import tutorial.webapp.models.Todo

case class TodoItem(todo: P[Todo]) extends Component[AppCommand] {
  override def render(get: Get): Node = {
    println(s"render TodoItem ${get(todo)}")
    val item = get(todo)
    val textSpan =
      if (item.done) E.span(DoneClass, Text(item.text))
      else E.span(Text(item.text))
    val button =
      if (item.done)
        E.button(Text("↩"), A.onClick(_ => emit(MarkAsUndone(item))))
      else E.button(Text("☑"), A.onClick(_ => emit(MarkAsDone(item))))
    val delete = E.button(Text("✖"), A.onClick(_ => emit(DeleteTodo(item))))
    E.li(E.span(button, Text(" "), delete, Text(" "), textSpan))
  }

  object DoneClass
      extends CssClass(S.textDecoration("line-through"), S.color("darkgray"))
}
