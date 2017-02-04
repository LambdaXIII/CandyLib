package candylib.signalslot

/**
  * Created by charlie on 17-2-4.
  */
trait SimpleSlot {
  val mainFunction: (AnyRef *) => Unit

  def run(args: AnyRef*): Unit = mainFunction(args)
}

object SimpleSlot {
  def apply(function: (AnyRef *) => Unit): SimpleSlot = new SimpleSlot {
    override val mainFunction: (AnyRef *) => Unit = function
  }
}
