package tutorial.webapp.components

import com.github.ahnfelt.react4s._
import tutorial.webapp.commands.AppCommand
import tutorial.webapp.models.Todo

case class TodoList(items: P[List[Todo]]) extends Component[AppCommand] {
  override def render(get: Get): Node = {
    if (get(items).nonEmpty)
      E.ul(A.id("items"),
           Tags(
             for (item <- get(items))
               yield
                 Component(TodoItem, item)
                   .withHandler(emit)
                   .withKey(item.id)))
    else
      E.div(A.id("empty"))
  }
}
