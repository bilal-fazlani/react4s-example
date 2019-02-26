package tutorial.webapp.components

import com.github.ahnfelt.react4s.{Text, _}
import tutorial.webapp.actions.{ClearAll, ClearCompleted}
import tutorial.webapp.models.Todo
import tutorial.webapp.state.AppCircuit

case class Footer() extends Component[NoEmit] {

  val items: State[List[Todo]] = State(AppCircuit.initialModel.todoItems)
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
        E.div(
          A.id("summary"),
          Text(s"All: $all, Completed: $completed, Active: $active")
        ),
        E.div(
          A.id("footer-actions"),
          E.span(
            Text("Clear all"),
            A.id("clear-button"),
            A.onClick(_ => {
              AppCircuit.dispatch(ClearAll)
            })),
          E.span(
            A.id("clear-completed-button"),
            Text("Clear completed"),
            A.onClick(_ => {
              AppCircuit.dispatch(ClearCompleted)
            }))
        )
      )
    else E.div(S.display("none"))
  }
}
