package tutorial.webapp.components

import com.github.ahnfelt.react4s._
import tutorial.webapp.commands.{AppCommand, ClearAll, ClearCompleted}
import tutorial.webapp.models.Todo

case class Footer(items: P[List[Todo]]) extends Component[AppCommand] {
  override def render(get: Get): Node = {
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
                  emit(ClearAll)
                })),
        E.input(A.`type`("button"),
                A.id("clear-completed-button"),
                A.value("Clear completed"),
                A.onClick(_ => {
                  emit(ClearCompleted)
                }))
      )
    else E.div(S.display("none"))
  }
}
