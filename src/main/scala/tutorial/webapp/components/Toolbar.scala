package tutorial.webapp.components

import com.github.ahnfelt.react4s._
import tutorial.webapp.commands.{AddTodo, ClearAll, AppCommand}

case class Toolbar() extends Component[AppCommand] {

  val text = State("")

  override def render(get: Get): Node = {
    E.form(
      A.id("toolbar"),
      A.onSubmit(e => {
        e.preventDefault()
        if (!get(text).trim.isEmpty) {
          emit(AddTodo(get(text)))
          text.set("")
        }
      }),
      E.input(A.id("input-box"),
              A.autoComplete("off"),
              A.onChangeText(text.set),
              A.value(get(text))),
      E.button(
        A.id("add-button"),
        E.img(A.src("images/add.png"))
      )
    )
  }
}
