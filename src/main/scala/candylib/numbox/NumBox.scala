package candylib.numbox

/**
  * Created by charlie on 17-2-10.
  *
  * A NumBox holds a number ( or other comparable value) with Top and Bottom
  * value limitation and a absolute value holder.
  *
  * @see [[AbsFilterSupport]],[[OrderedValueHolder]],[[TopBottomSupport]]
  *
  */
class NumBox[T <: Comparable[T]]
  extends OrderedValueHolder[T]
    with TopBottomSupport[T]
    with AbsFilterSupport {

  private var _value: T = _

  override def value_=(v: T): Unit = absFilter(v) match {
    case m: T if isTooHigh(m) => _value = top
    case m: T if isTooLow(m) => _value = bottom
    case m: T => _value = m
  }

  override def value: T = _value

}

/**
  * Factory functions of NumBox
  */
object NumBox {
  def apply[T <: Comparable[T]](min: T, max: T, v: T): NumBox[T] = new NumBox[T] {
    area = (min, max)
    value = v
  }

  def apply(min: Int, max: Int, v: Int): NumBox[java.lang.Integer] = new NumBox[Integer] {
    area = (min, max)
    value = v
  }

  def apply(min: Double, max: Double, v: Double): NumBox[java.lang.Double] = new NumBox[java.lang.Double] {
    area = (min, max)
    value = v
  }

  def apply(min: Int, max: Int): NumBox[java.lang.Integer] = apply(min, max, min)

  def apply(min: Double, max: Double): NumBox[java.lang.Double] = apply(min, max, min)

  def apply[T <: Comparable[T]](min: T, max: T): NumBox[T] = apply(min, max, min)

}
