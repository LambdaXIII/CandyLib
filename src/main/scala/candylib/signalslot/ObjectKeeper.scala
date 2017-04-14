package candylib.signalslot

/**
  * Created by a on 2017/4/13.
  */
class ObjectKeeper[T](o:T) {
  val editStatusChangedSignal = new SimpleSignal[Boolean]("Treasure Edited.")

  private val _t:T = o
  def treasure:T = _t

  private var _edited = false
  def edited:Boolean = _edited
  def edited_=(v:Boolean):Unit = {
    _edited = v
    editStatusChangedSignal.emit(_edited)
  }

  def setSaved():Unit = edited = false
  def isSaved():Boolean = ! edited

}

object ObjectKeeper
{
  def apply[T](o: T): ObjectKeeper[T] = new ObjectKeeper(o)
}
