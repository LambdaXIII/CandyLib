package candylib.signalslot

import java.io.File

/**
  * Created by a on 2017/4/13.
  */
class ObjectKeeper[T] {

  /*
  Signals
   */
  val statusChangedSignal:SimpleSignal[Boolean] = new SimpleSignal[Boolean]("edited status changed")

  val fileOpenedSignal:SimpleSignal[File] = new SimpleSignal[File]("file opened")
  val fileSavedSignal:SimpleSignal[File] = new SimpleSignal[File]("file saved")

  protected var _treasure: T = _
  protected var _file:File = _

  def treasure: T = _treasure
  def currentFile:File = _file

  var openFunction:(File)=>T = _
  var saveFunction:(T,File)=>Unit = _

  def open(file:File):Unit = {
    _treasure = openFunction.apply(file)
    _file = file
    edited = false
    fileOpenedSignal.emit(currentFile)
  }

  def open(filename:String):Unit = open(new File(filename))

  def save():Unit = {
    saveFunction.apply(treasure, currentFile)
    edited = false
    fileSavedSignal.emit(currentFile)
  }

  def saveAs(file:File):Unit = {
    saveFunction.apply(treasure, file)
    fileSavedSignal.emit(file)
    _file = file
    fileOpenedSignal.emit(currentFile)
  }

  def saveAs(filename:String):Unit = saveAs(new File(filename))

  private var _edited = false

  def edited: Boolean = _edited

  def edited_=(v: Boolean): Unit = {
    if (_edited != v){
      statusChangedSignal.emit(v)
      _edited = v
    }
  }


  /*
  Slots
   */
  val treasureEditedSlot:SimpleSlot[Boolean] = new SimpleSlot[Boolean] {
    override val mainFunction: (Boolean) => Unit = (x) => edited = true
  }
  val editedStatusSlot:SimpleSlot[Boolean] = new SimpleSlot[Boolean] {
    override val mainFunction: (Boolean) => Unit = (x) => edited = x
  }
}

object ObjectKeeper{
  def apply[T](): ObjectKeeper[T] = new ObjectKeeper[T]()
}
