package candylib.signalslot

/**
  * Created by charlie on 17-2-12.
  */
class SimpleProperty[T](x: T) {
  private var _value: T = x

  val valueChangedSignal = new SimpleSignal[T]("Value Changed.")
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
