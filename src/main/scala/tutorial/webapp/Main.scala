package tutorial.webapp

import com.github.ahnfelt.react4s.{Component, ReactBridge}
import tutorial.webapp.components.TodoApp

object Main extends App {
  ReactBridge.renderToDomById(Component(TodoApp), "main")
}
