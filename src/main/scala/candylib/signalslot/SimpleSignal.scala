package candylib.signalslot

import scala.collection.mutable.ListBuffer

/**
  * Created by charlie on 17-2-4.
  */
class SimpleSignal[T](desc: String = "") extends SimpleSlot[T] {
  override val mainFunction: (T) => Unit = (x: T) => emit(x)

  var description: String = desc

  private val targets = new ListBuffer[SimpleSlot[T]]()

  def emit(x: T): Unit = {
    targets.foreach(slot => slot.run(x))
  }

  def emit(): Unit = {
    targets.foreach(slot => slot.run(_))
  }

  def connect(slot: SimpleSlot[T]): Unit = {
    targets += slot
  }

  def connect(slotFunction: (T) => Unit): Unit = {
    targets += new SimpleSlot[T] {
      override val mainFunction: (T) => Unit = slotFunction
    }
  }

  def disConnect(slot: SimpleSlot[T]): Unit = {
    targets -= slot
  }

  def disConnect(slotFunction: ((T) => Unit)): Unit = {
    targets -= targets.find(slot => slot.mainFunction == slotFunction).get
  }

  def disConnectAll(): Unit = {
    targets.clear()
  }

  def <<<(slot: SimpleSlot[T]): Unit = connect(slot)

  def <<<(func: (T) => Unit): Unit = connect(func)

  def >>>(slot: SimpleSlot[T]): Unit = disConnect(slot)

  def >>>(func: (T) => Unit): Unit = disConnect(func)

}

object SimpleSignal {
  def apply[T](desc: String = ""): SimpleSignal[T] = new SimpleSignal[T](desc)
}
