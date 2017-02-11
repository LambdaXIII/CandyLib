package candylib.numbox

/**
  * Created by charlie on 17-2-10.
  */
trait TopBottomSupport[T <: Comparable[T]] {
  private var _top: T = _
  private var _bottom: T = _

  def top: T = _top

  def top_=(value: T): Unit = _top = value

  def bottom: T = _bottom

  def bottom_=(value: T): Unit = _bottom = value

  def contains(v: T): Boolean = (v.compareTo(top) <= 0) && (v.compareTo(bottom) >= 0)

  def isTooHigh(v: T): Boolean = v.compareTo(top) > 0

  def isTooLow(v: T): Boolean = v.compareTo(bottom) < 0

  def isOnTop(v: T): Boolean = v == top

  def isOnBottom(v: T): Boolean = v == bottom

  def isOnEdge(v: T): Boolean = isOnTop(v) || isOnBottom(v)

  def area: (T, T) = (bottom, top)

  def area_=(v: (T, T)): Unit = if (v._1.compareTo(v._2) <= 0) {
    bottom = v._1
    top = v._2
  } else throw new IllegalArgumentException("Top value must be on top of the bottom value.")

}
