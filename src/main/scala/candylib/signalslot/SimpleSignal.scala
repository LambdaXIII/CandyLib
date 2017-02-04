package candylib.signalslot

import scala.collection.mutable.ListBuffer

/**
  * Created by charlie on 17-2-4.
  */
class SimpleSignal(desc: String = "") extends SimpleSlot {
  override val mainFunction: (Any) => Unit = (x: Any) => emit(x)

  var description: String = desc

  private val targets = new ListBuffer[SimpleSlot]()

  def emit(x: Any): Unit = {
    targets.foreach(slot => slot.run(x))
  }

  def connect(slot: SimpleSlot): Unit = {
    targets += slot
  }

  def connect(slotFunction: (Any) => Unit): Unit = {
    targets += new SimpleSlot {
      override val mainFunction: (Any) => Unit = slotFunction
    }
  }

  def disConnect(slot: SimpleSlot): Unit = {
    targets -= slot
  }

  def disConnect(slotFunction: ((Any) => Unit)): Unit = {
    targets -= targets.find(slot => slot.mainFunction == slotFunction).get
  }

  def clearConnections(): Unit = {
    targets.clear()
  }

  def <<<(slot: SimpleSlot): Unit = connect(slot)

  def <<<(func: (Any) => Unit): Unit = connect(func)

  def >>>(slot: SimpleSlot): Unit = disConnect(slot)

  def >>>(func: (Any) => Unit): Unit = disConnect(func)

}

object SimpleSignal {
  def apply(desc: String = ""): SimpleSignal = new SimpleSignal(desc)
}
