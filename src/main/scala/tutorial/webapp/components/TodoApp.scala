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
    println("mark as done")
    val xs = get(data).map {
      case `todo` =>
        println("marked as done")
        todo.copy(done = true)
      case x => x
    }
    data.set(xs)
  }

  def markAsUndone(todo: Todo)(implicit get: Get): Unit = {
    println("mark as undone")
    val xs = get(data).map {
      case `todo` =>
        println("marked as undone")
        todo.copy(done = false)
      case x => x
    }
    data.set(xs)
  }

  def deleteTodo(todo: Todo)(implicit get: Get): Unit =
    data.set(get(data).filter(_ != todo))

  def addTodo(text: String)(implicit get: Get): Unit = {
    val newTodo = Todo(UUID.randomUUID().toString, text)
    data.set(get(data) :+ newTodo)
  }
}
