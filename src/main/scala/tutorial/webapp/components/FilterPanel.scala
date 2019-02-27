package tutorial.webapp.components

import com.github.ahnfelt.react4s._
import tutorial.webapp.actions.ApplyFilter
import tutorial.webapp.models.{All, Completed, NotCompleted}
import tutorial.webapp.state.AppCircuit

case class FilterPanel() extends Component[NoEmit]{

  val rootState = State(AppCircuit.initialModel)

  AppCircuit.subscribe(AppCircuit.zoom(identity)){modelRO =>
    rootState.set(modelRO())
  }

  override def render(get: Get): Node = {
    val todos = get(rootState).todoItems
    val filter = get(rootState).filter
    val all = todos.length
    val completed = todos.count(_.done)
    val active = todos.count(!_.done)

    E.div(
      A.id("summary"),
      E.span(
        Text(s"All: $all"),
        A.className(if(filter == All) "active-filter" else "disabled"),
        A.onClick(_=> AppCircuit.dispatch(ApplyFilter(All)))
      ),
      E.span(
        Text(s"Completed: $completed"),
        A.className(if(filter == Completed) "active-filter" else "disabled"),
        A.onClick(_=> AppCircuit.dispatch(ApplyFilter(Completed)))
      ),
      E.span(
        Text(s"Not Completed: $active"),
        A.className(if(filter == NotCompleted) "active-filter" else "disabled"),
        A.onClick(_=> AppCircuit.dispatch(ApplyFilter(NotCompleted)))
      )
    )
  }
}
