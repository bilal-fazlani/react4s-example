package todoApi

import java.util.{Timer, TimerTask}

import scala.concurrent.duration.FiniteDuration
import scala.concurrent.{Future, Promise}
import scala.util.Try

object Extensions {
  implicit class RichFiniteDuration(duration: FiniteDuration) {

    def await: Future[Unit] = await(() => ())

    def await[T](block: => T): Future[T] = {
      val promise = Promise[T]()
      val t = new Timer()
      t.schedule(new TimerTask {
        override def run(): Unit = {
          promise.complete(Try(block))
        }
      }, duration.toMillis)
      promise.future
    }
  }
}
