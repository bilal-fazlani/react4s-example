package tutorial.webapp.components

import com.github.ahnfelt.react4s._

case class CounterApp() extends Component[NoEmit] {

  val count = State(0)

  override def render(get: Get): Node = {
    E.div(
      E.h1(Text("Counter")),
      E.br(),
      E.h3(Text(s"value: ${get(count)}")),
      E.br(),
      E.button(
        S.width("40px"),
        E.h1(Text("+")),
        A.onClick(_ => count.modify(_ + 1)),
        A.disabled(if (get(count) == 10) "true" else null),
      ),
      E.button(
        A.onClick(_ => count.modify(_ - 1)),
        A.disabled(if (get(count) == 0) "true" else null),
        S.width("40px"),
        E.h1(Text("-"))
      ),
      E.button(
        A.onClick(_ => count.set(0)),
        A.disabled(if (get(count) == 0) "true" else null),
        E.h1(Text("RESET"))
      )
    )
  }
}
