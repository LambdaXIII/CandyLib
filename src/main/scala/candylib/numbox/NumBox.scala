package candylib.numbox

/**
  * Created by charlie on 17-2-10.
  */
class NumBox[T <: Comparable[T]] extends ComparableValueHolder[T] with RangeSupport[T] with AbsFilterSupport {
  private var _value: T = _

  override def value_=(v: T): Unit = absFilter(v) match {
    case m: T if isTooHigh(m) => _value = top
    case m: T if isTooLow(m) => _value = bottom
    case m: T => _value = m
  }

  override def value: T = _value

}
