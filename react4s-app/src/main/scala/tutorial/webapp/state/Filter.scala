package tutorial.webapp.state

sealed abstract class Filter {
  def predicate: Todo => Boolean
}

case object All extends Filter {
  override def predicate: Todo => Boolean = _ => true
}

case object Completed extends Filter {
  override def predicate: Todo => Boolean = _.done
}

case object NotCompleted extends Filter {
  override def predicate: Todo => Boolean = !_.done
}
