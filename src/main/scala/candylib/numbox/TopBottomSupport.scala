package candylib.numbox

/**
  * Created by charlie on 17-2-10.
  *
  * Trait provides top and bottom value supports.
  *
  * Top and bottom value means Max and Min value.
  *
  */
trait TopBottomSupport[T <: Comparable[T]] {
  private var _top: T = _
  private var _bottom: T = _

  def top: T = _top

  def top_=(value: T): Unit = _top = value

  def bottom: T = _bottom

  def bottom_=(value: T): Unit = _bottom = value

  /**
    * See if the value is in the area(between Top and Bottom value)
    * @param v value which is going to judge
    * @return true if v is in the area
    */
  def contains(v: T): Boolean = (v.compareTo(top) <= 0) && (v.compareTo(bottom) >= 0)

  /**
    *
    * @param v
    * @return true if v is higher than Top value.
    */
  def isTooHigh(v: T): Boolean = v.compareTo(top) > 0

  /**
    *
    * @param v
    * @return true if v is lower than Bottom value.
    */
  def isTooLow(v: T): Boolean = v.compareTo(bottom) < 0

  /**
    *
    * @param v
    * @return true if v is equal to the Top value.
    */
  def isOnTop(v: T): Boolean = v == top

  /**
    *
    * @param v
    * @return true if v is equal to the Bottom value.
    */
  def isOnBottom(v: T): Boolean = v == bottom

  /**
    *
    * @param v
    * @return true if v is equal to the Top or Bottom value.
    */
  def isOnEdge(v: T): Boolean = isOnTop(v) || isOnBottom(v)

  def area: (T, T) = (bottom, top)

  def area_=(v: (T, T)): Unit = if (v._1.compareTo(v._2) <= 0) {
    bottom = v._1
    top = v._2
  } else throw new IllegalArgumentException("Top value must be on top of the bottom value.")

}
