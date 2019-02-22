package tutorial.webapp.components

import com.github.ahnfelt.react4s._

case class Toolbar() extends Component[String] {

  val text = State("")

  override def render(get: Get): Node = {
    E.div(
      E.form(A.onSubmit(e => {
               e.preventDefault()
               emit(get(text))
               text.set("")
             }),
             E.input(A.onChangeText(text.set), A.value(get(text))),
             E.button(Text("Add")))
    )
  }
}
