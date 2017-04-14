package candylib.functions

/**
  * Created by Charlie on 2017/2/4.
  *
  * A really simple structure to hold a Boolean value.
  *
  * It supports many logical calculating.
  *
  * (Maybe this is useless.
  */
class LogicLock(init_status:Boolean = false) {
  var status:Boolean = init_status

  def toggle():Boolean = {
    status = ! status
    status
  }

  def isTrue:Boolean = status

  def isFalse:Boolean = ! status

  def setTrue():Unit = status = true
  def setFalse():Unit = status = false

  def stringStatus:String = Map(true -> "True", false -> "False")(status)
  def stringStatus_=(value:String):Unit = status = value.trim.toLowerCase == "true"

  def intStatus:Int = Map(true -> 1, false -> 0)(status)
  def intStatus_=(value:Int):Unit = status = !(value == 0)

  def ==(value:Boolean):Boolean = status == value
  def ==(value:LogicLock):Boolean = status == value.status

  override def toString: String = status.toString

  def ||(other:LogicLock):Boolean = status || other.status
  def ||(other:Boolean):Boolean = status || other

  def &&(other:LogicLock):Boolean = status && other.status
  def &&(other:Boolean):Boolean = status && other
}


object LogicLock {
  def apply(init_status: Boolean = false): LogicLock = new LogicLock(init_status)

  def apply(stringValue: String): LogicLock = new LogicLock(){
    stringStatus = stringValue
  }

  def apply(intValue: Int): LogicLock = new LogicLock(){
    intStatus = intValue
  }

  def True:LogicLock = new LogicLock(true)
  def False:LogicLock = new LogicLock(false)
}