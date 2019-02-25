package tutorial.webapp

package object actions {
  import diode.Action

  trait AppAction extends Action

  case class Increase(amount: Int) extends AppAction
  case class Decrease(amount: Int) extends AppAction
  case object Reset extends AppAction
}
