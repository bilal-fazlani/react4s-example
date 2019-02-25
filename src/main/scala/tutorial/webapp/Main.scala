package tutorial.webapp

import com.github.ahnfelt.react4s.{Component, ReactBridge}
import tutorial.webapp.components.{CounterApp}

object Main extends App {
  ReactBridge.renderToDomById(Component(CounterApp), "main")
}
