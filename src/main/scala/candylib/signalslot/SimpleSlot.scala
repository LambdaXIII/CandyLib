package candylib.signalslot

/**
  * Created by charlie on 17-2-4.
  *
  * A simple object holds a Unit function.
  * And the function can be trigger by 'run()' function.
  *
  * Since this is a trait, so you should make a new subclass to use it.
  *
  * The purpose to use SimpleSlot to hold a function is that you can connect
  * a slot to a [[SimpleSignal]].
  *
  * @example {{{
  *           var value:Int = 0
  *           val changeValueSlot = new SimpleSlot[Int]{
  *             override val mainFunction:(Int) => Unit = (x) => value = x
  *           }
  *           changeValueSlot.run(20)
  * }}}
  * Then the value will change to 20.
  *
  * @see [[SimpleSignal]]
  */
trait SimpleSlot[T] {
  val mainFunction: (T) => Unit

  /**
    * trigger the function.
    * @param args the variable send to the slot.
    */
  def run(args: T): Unit = mainFunction(args)
}

object SimpleSlot {
  def apply[T](function: (T) => Unit): SimpleSlot[T] = new SimpleSlot[T] {
    override val mainFunction: (T) => Unit = function
  }

  def apply(function: () => Unit): SimpleSlot[Any] = new SimpleSlot[Any] {
    override val mainFunction: (Any) => Unit = (any: Any) => function
  }
}
