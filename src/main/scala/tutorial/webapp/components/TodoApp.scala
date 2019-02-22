package tutorial.webapp.components

import java.util.UUID

import com.github.ahnfelt.react4s._
import tutorial.webapp.commands._
import tutorial.webapp.models.Todo

case class TodoApp() extends Component[NoEmit] {
  val data = State(List.empty[Todo])

  override def render(get: Get): Node = {
    println("render TodoApp")
    implicit val getImplicit: Get = get

    E.div(
      Component(Header),
      Component(Toolbar).withHandler {
        case AddTodo(text) => addTodo(text)
        case ClearAll      => data.set(List.empty)
      },
      Component(TodoList, get(data)).withHandler {
        case MarkAsDone(todo)   => markAsDone(todo)
        case MarkAsUndone(todo) => markAsUndone(todo)
        case DeleteTodo(todo)   => deleteTodo(todo)
      }
    )
  }

  def markAsDone(todo: Todo)(implicit get: Get): Unit = {
    data.modify(_.map {
      case `todo` => todo.copy(done = true)
      case x      => x
    })
  }

  def markAsUndone(todo: Todo)(implicit get: Get): Unit = {
    data.modify(_.map {
      case `todo` => todo.copy(done = false)
      case x      => x
    })
  }

  def deleteTodo(todo: Todo)(implicit get: Get): Unit =
    data.modify(_.filter(_ != todo))

  def addTodo(text: String)(implicit get: Get): Unit = {
    data.modify(_ :+ Todo(UUID.randomUUID().toString, text))
  }
}
