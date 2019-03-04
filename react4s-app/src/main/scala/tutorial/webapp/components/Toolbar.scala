package tutorial.webapp.components

import com.github.ahnfelt.react4s._
import tutorial.webapp.Actions.AddTodo
import tutorial.webapp.state.AppCircuit

case class Toolbar() extends Component[NoEmit] {

  val text = State("")

  override def render(get: Get): Node = {
    println(s"render: ${getClass.getSimpleName}")
    E.form(
      A.id("toolbar"),
      A.onSubmit(e => {
        e.preventDefault()
        if (!get(text).trim.isEmpty) {
          AppCircuit.dispatch(AddTodo(get(text)))
          text.set("")
        }
      }),
      E.input(A.id("input-box"),
              A.autoComplete("off"),
              A.placeholder("what needs to be done?"),
              A.onChangeText(text.set),
              A.value(get(text))),
      E.button(
        A.id("add-button"),
        E.img(A.src("images/add.png"))
      )
    )
  }
}
