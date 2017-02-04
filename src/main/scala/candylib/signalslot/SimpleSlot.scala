package candylib.signalslot

/**
  * Created by charlie on 17-2-4.
  */
trait SimpleSlot {
  val mainFunction: (Any *) => Unit

  def run(args: Any*): Unit = mainFunction.apply(args)
}

object SimpleSlot {
  def apply(function: (Any *) => Unit): SimpleSlot = new SimpleSlot {
    override val mainFunction: (Any) => Unit = function
  }
}
