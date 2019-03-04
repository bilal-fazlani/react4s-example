package tutorial.webapp.components

import com.github.ahnfelt.react4s._
import tutorial.webapp.components.todo_item.TodoItemRow
import tutorial.webapp.state.AppCircuit

case class TodoList() extends Component[NoEmit] {

  val root = State(AppCircuit.initialModel)

  AppCircuit.subscribe(AppCircuit.zoom(identity))(modelRO ⇒ root.set(modelRO()))

  override def render(get: Get): Node = {

    val filter = get(root).filter
    val items = get(root).todoItems.filter(filter.predicate)

    println(s"render: ${getClass.getSimpleName}")
    if (items.nonEmpty)
      E.ul(A.id("items"),
           Tags(
             for (item <- items)
               yield
                 Component(TodoItemRow, item)
                   .withKey(item.id)))
    else
      E.div(A.id("empty"))
  }
}
