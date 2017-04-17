package candylib.numbox

/**
  * Created by charlie on 17-2-10.
  *
  * This trait provides a switch to control a absolute number filter.
  *
  * @example {{{
  *           object A extends AbsFilterSupport
  *           A.withAbs = true
  *           A.absFilter(-20) == 20
  * }}}
  */
trait AbsFilterSupport {
  private var _withAbs: Boolean = false

  /**
    * The switch controls the filter.
    * @return returns true if filter number to the absolute value
    */
  def withAbs: Boolean = _withAbs

  /**
    * Set the switch status
    * @param v set to true then the filter will trans the falue to a absolute value.
    */
  def withAbs_=(v: Boolean): Unit = if (v) _withAbs = true else _withAbs = false

  /**
    * A not-so-good function to translate a value to its absolute value.
    * @param v value input
    * @return if 'withAbs' switch is set to true and the translation is supported,
    *         return the absolute value, otherwise return itself
    */
  protected def absFilter(v: Any) = if (withAbs)
    v match {
      case m: Int => Math.abs(m)
      case m: Double => Math.abs(m)
      case m: Long => Math.abs(m)
      case m: Float => Math.abs(m)
      case m: java.lang.Integer => Math.abs(m)
      case m: java.lang.Double => Math.abs(m)
      case _ => v
    }
  else v

}
