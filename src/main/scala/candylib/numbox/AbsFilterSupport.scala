package candylib.numbox

/**
  * Created by charlie on 17-2-10.
  */
trait AbsFilterSupport {
  private var _withAbs: Boolean = false

  def withAbs: Boolean = _withAbs

  def withAbs_=(v: Boolean): Unit = if (v) _withAbs = true else _withAbs = false

  def absFilter[T](v: T): Any = if (withAbs)
    v match {
      case m: Int => Math.abs(m)
      case m: Double => Math.abs(m)
      case m: Long => Math.abs(m)
      case m: Float => Math.abs(m)
      case m: Integer => Math.abs(m)
      case _ => v
    }
  else v

}
