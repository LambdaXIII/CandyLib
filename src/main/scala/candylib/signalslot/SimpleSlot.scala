package candylib.signalslot

/**
  * Created by charlie on 17-2-4.
  */
trait SimpleSlot[T] {
  val mainFunction: (T) => Unit

  def run(args: T): Unit = mainFunction(args)
}

object SimpleSlot {
  def apply[T](function: (T) => Unit): SimpleSlot[T] = new SimpleSlot[T] {
    override val mainFunction: (T) => Unit = function
  }

  def apply(function: () => Unit): SimpleSlot[Int] = new SimpleSlot[Int] {
    override val mainFunction: (Int) => Unit = (x: Int) => function
  }
}
