package candylib.signalslot

/**
  * Created by a on 2017/4/13.
  */
class ObjectKeeper[T]() {
  val editStatusChangedSignal = new SimpleSignal[Boolean]("Treasure Edited.")

  private var _t: T = _

  def treasure: T = _t

  def treasure_=(v: T): Unit = _t = v

  def this(o:T) = {
    this()
    treasure = o
  }

  private var _edited = false

  def edited: Boolean = _edited

  def edited_=(v: Boolean): Unit = {
    _edited = v
    editStatusChangedSignal.emit(_edited)
  }

  def setSaved(): Unit = edited = false

  def isSaved(): Boolean = !edited

}

object ObjectKeeper {
  def apply[T](o: T): ObjectKeeper[T] = new ObjectKeeper(o)
}
