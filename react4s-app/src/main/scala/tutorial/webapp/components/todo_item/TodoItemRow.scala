package tutorial.webapp.components.todo_item

import com.github.ahnfelt.react4s._
import tutorial.webapp.state.{FailedTodo, SavedTodo, Todo, UnsavedTodo}

case class TodoItemRow(todo: P[Todo]) extends Component[NoEmit] {
  override def render(get: Get): Node = {

    val item = get(todo)

    println(s"render: ${getClass.getSimpleName}, $item")

    item match {
      case unsaved: UnsavedTodo => Component(UnsavedTodoItemRow, unsaved)
      case saved: SavedTodo     => Component(SavedTodoItemRow, saved)
      case failed: FailedTodo   => Component(FailedTodoItemRow, failed)
    }

  }
}
