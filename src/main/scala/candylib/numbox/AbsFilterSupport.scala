package candylib.numbox

/**
  * Created by charlie on 17-2-10.
  */
trait AbsFilterSupport {
  private var _withAbs: Boolean = false

  def withAbs: Boolean = _withAbs

  def withAbs_=(v: Boolean): Unit = if (v) _withAbs = true else _withAbs = false

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
