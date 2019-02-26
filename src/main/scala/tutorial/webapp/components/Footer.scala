package tutorial.webapp.components

import com.github.ahnfelt.react4s._
import tutorial.webapp.actions.{ClearAll, ClearCompleted}
import tutorial.webapp.models.Todo
import tutorial.webapp.state.AppCircuit

case class Footer() extends Component[NoEmit] {

  val items: State[List[Todo]] = State(List.empty)
  AppCircuit.subscribe(AppCircuit.zoom(_.todoItems)) { modelRO =>
    items.set(modelRO())
  }

  override def render(get: Get): Node = {
    println(s"render: ${getClass.getSimpleName}")
    val todos = get(items)
    val all = todos.length
    val completed = todos.count(_.done)
    val active = todos.count(!_.done)
    if (all > 0)
      E.div(
        A.id("footer"),
        E.span(Text(s"All: $all, Completed: $completed, Active: $active")),
        E.input(A.`type`("button"),
                A.id("clear-button"),
                A.value("Clear all"),
                A.onClick(_ => {
                  AppCircuit.dispatch(ClearAll)
                })),
        E.input(A.`type`("button"),
                A.id("clear-completed-button"),
                A.value("Clear completed"),
                A.onClick(_ => {
                  AppCircuit.dispatch(ClearCompleted)
                }))
      )
    else E.div(S.display("none"))
  }
}
