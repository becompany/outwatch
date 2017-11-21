package outwatch

import cats.effect.IO
import outwatch.Sink.{ObservableSink, SubjectSink}
import outwatch.dom.Observable

object Handler {
  private[outwatch] def apply[T](sink: Sink[T], source: Observable[T]): Handler[T] =
    new ObservableSink[T, T](sink, source)

  /**
    * This function also allows you to create initial values for your newly created Handler.
    * This is equivalent to calling `startWithMany` with the given values.
    *
    * @param seeds a sequence of initial values that the Handler will emit.
    * @tparam T the type parameter of the elements
    * @return the newly created Handler.
    */
  def create[T](seeds: T*): IO[Handler[T]] = Pipe.create[T](seeds: _*)

  def create[T]: IO[Handler[T]] = Pipe.create[T]

}

