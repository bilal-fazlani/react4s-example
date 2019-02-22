package tutorial.webapp.components

import com.github.ahnfelt.react4s._
import tutorial.webapp.commands.AppCommand
import tutorial.webapp.models.Todo

case class TodoList(items: P[List[Todo]]) extends Component[AppCommand] {
  override def render(get: Get): Node = {
    println("render TodoList")
    E.div(
      E.ul(Styles,
           Tags(
             for (item <- get(items))
               yield
                 Component(TodoItem, item)
                   .withHandler(emit)
                   .withKey(item.id)))
    )
  }

  object Styles extends CssClass(S.listStyle("none"), S.paddingLeft("0px"))
}
