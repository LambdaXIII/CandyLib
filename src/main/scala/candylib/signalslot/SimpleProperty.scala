package candylib.signalslot

/**
  * Created by charlie on 17-2-12.
  *
  * A variable holder with a pair of signal and slot.
  *
  * 'changeValueSlot' will change the holding value.
  *
  * 'valueChangedSignal' will emit when the value changed.
  * No matter the value is changed by the slot or not.
  *
  * @see [[SimpleSignal]], [[SimpleSlot]]
  */
class SimpleProperty[T](x: T) {
  private var _value: T = x

  /**
    * The signal that will emit when the value changed.
    */
  val valueChangedSignal = new SimpleSignal[T]("Value Changed.")

  /**
    * The slot will change the value.
    */
  var changeValueSlot = new SimpleSlot[T] {
    override val mainFunction: (T) => Unit = (x: T) => value = x
  }

  def value: T = _value

  def value_=(v: T): Unit = {
    _value = v
    valueChangedSignal.emit(v)
  }

}

object SimpleProperty {
  def apply(x: Any) = new SimpleProperty(x)
}
