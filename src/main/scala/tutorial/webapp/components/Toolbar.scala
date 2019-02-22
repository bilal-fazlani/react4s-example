package tutorial.webapp.components

import com.github.ahnfelt.react4s._
import tutorial.webapp.commands.{AddTodo, ClearAll, AppCommand}

case class Toolbar() extends Component[AppCommand] {

  val text = State("")

  override def render(get: Get): Node = {
    E.div(
      E.form(
        A.onSubmit(e => {
          e.preventDefault()
          if (!get(text).trim.isEmpty) {
            emit(AddTodo(get(text)))
            text.set("")
          }
        }),
        E.input(A.onChangeText(text.set), A.value(get(text))),
        E.button(Text("Add"), S.backgroundColor("lightgreen")),
        E.input(A.`type`("button"),
                S.backgroundColor("pink"),
                A.value("Clear all"),
                A.onClick(_ => {
                  emit(ClearAll)
                }))
      )
    )
  }
}
