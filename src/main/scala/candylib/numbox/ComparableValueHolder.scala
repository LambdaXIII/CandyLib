package candylib.numbox

/**
  * Created by charlie on 17-2-10.
  */
trait ComparableValueHolder[T <: Comparable[T]] {
  def value: T

  def value_=(v: T): Unit

  def :=(v: T): Unit = value = v

  def <(v: T): Boolean = value.compareTo(v) < 0

  def <=(v: T): Boolean = value.compareTo(v) <= 0

  def >(v: T): Boolean = value.compareTo(v) > 0

  def >=(v: T): Boolean = value.compareTo(v) >= 0

}
