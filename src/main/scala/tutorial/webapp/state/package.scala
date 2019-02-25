package tutorial.webapp

import diode._
import tutorial.webapp.actions._

package object state {
  case class RootModel(counter: Int)

  object AppCircuit extends Circuit[RootModel] {
    override protected def initialModel: RootModel = RootModel(0)

    val counterHandler: ActionHandler[RootModel, Int] = new ActionHandler(
      zoomTo(_.counter)) {
      override protected def handle = {
        case Increase(amount) => updated(value + amount)
        case Decrease(amount) => updated(value - amount)
        case Reset            => updated(0)
      }
    }

    override protected def actionHandler = composeHandlers(counterHandler)
  }
}
