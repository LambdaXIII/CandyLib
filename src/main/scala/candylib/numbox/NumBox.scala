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

object NumBox {
  def apply[T <: Comparable[T]](a: (T, T), v: T): NumBox[T] = new NumBox[T] {
    area = a
    value = v
  }

  def apply[T <: Comparable[T]](min: T, max: T, v: T): NumBox[T] = apply((min, max), v)

  def apply[T <: Comparable[T]](a: (T, T)): NumBox[T] = apply(a, a._1)

  def apply[T <: Comparable[T]](min: T, max: T): NumBox[T] = apply((min, max))

  def apply(v: Int): NumBox[Integer] = new NumBox[java.lang.Integer] {
    bottom = v - 4 * v
    top = v + 3 * v
    value = v
  }

  def apply(v: Double): NumBox[java.lang.Double] = new NumBox[java.lang.Double] {
    bottom = v - 4 * v
    top = v + 3 * v
    value = v
  }

}
