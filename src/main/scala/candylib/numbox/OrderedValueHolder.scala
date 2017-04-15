package candylib.numbox

/**
  * Created by charlie on 17-2-10.
  *
  * A trait holding a comparable value.
  * Maybe not only a number.
  *
  * Supports comparing functions.
  *
  * @see [[Comparable]]
  */
trait OrderedValueHolder[T <: Comparable[T]] {
  def value: T

  def value_=(v: T): Unit

  def <(v: T): Boolean = value.compareTo(v) < 0

  def <=(v: T): Boolean = value.compareTo(v) <= 0

  def >(v: T): Boolean = value.compareTo(v) > 0

  def >=(v: T): Boolean = value.compareTo(v) >= 0

}
