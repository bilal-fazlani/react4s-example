package tutorial.webapp.components

import com.github.ahnfelt.react4s._
import tutorial.webapp.models.Todo
import tutorial.webapp.state.AppCircuit

case class TodoList() extends Component[NoEmit] {

  val items: State[List[Todo]] = State(AppCircuit.initialModel.todoItems)

  AppCircuit.subscribe(AppCircuit.zoom(_.todoItems))(modelRO ⇒
    items.set(modelRO()))

  override def render(get: Get): Node = {
    println(s"render: ${getClass.getSimpleName}")
    if (get(items).nonEmpty)
      E.ul(A.id("items"),
           Tags(
             for (item <- get(items))
               yield
                 Component(TodoItem, item)
                   .withKey(item.id)))
    else
      E.div(A.id("empty"))
  }
}
