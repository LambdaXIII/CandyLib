package candylib.signalslot

import scala.collection.mutable.ListBuffer

/**
  * Created by charlie on 17-2-4.
  *
  * When you emit a signal it will trigger all slots connected.
  *
  * SimpleSignal holds a List of [[SimpleSlot]],
  * when you emit a signal it will trigger all slots in the list
  * one by one.
  *
  * Since SimpleSignal is also a [[SimpleSlot]], so a signal can
  * also be connected to another signal. When the signal emits,
  * it will emit the signal connected.
  *
  * Use signals and slots in different classes and connect then
  * together is a modern way to control callback functions.
  * (Actually it is not my idea.)
  *
  * @example {{{
  *           val v:Int = 0
  *           val changeValueSlot = new SimpleSlot[Int]{
  *             override val mainFunction = (x) => v = x
  *           }
  *
  *           val valueWillChangeSignal = new SimpleSignal[Int]("when the value should change")
  *
  *           valueWillChangeSignal <<< changeValueSlot
  *
  *           valueWillChangeSignal.emit(20)
  * }}}
  *
  * Then the v will change to 20.
  *
  * @constructor T is the type of the variable you wish to pass to the slots.
  *              Only signals and slots with same type can be connect together.
  *
  * @param desc You must give a describe string to a signal. Even it is useless.
  *
  * @see [[SimpleSlot]]
  */
class SimpleSignal[T](desc: String = "") extends SimpleSlot[T] {
  override val mainFunction: (T) => Unit = (x: T) => emit(x)

  var description: String = desc

  private val targets = new ListBuffer[SimpleSlot[T]]()

  /**
    * Emit the signal with the given value.
    * @param x variable will pass to slots.
    */
  def emit(x: T): Unit = {
    targets.foreach(slot => slot.run(x))
  }

  /**
    * Emit the signal with no value pass.
    */
  def emit(): Unit = {
    targets.foreach(slot => slot.run(_))
  }

  def connect(slot: SimpleSlot[T]): Unit = {
    targets += slot
  }

  /**
    * connect to a function.
    *
    * SimpleSignal will create a slot and connect to.
    * @param slotFunction the function.
    */
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

  /**
    * Clear the connections.
    */
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
