package candylib.signalslot

import java.io.{File, IOException}

/**
  * Created by a on 2017/4/13.
  *
  * A keeper keeps an object parsed from a file.
  *
  * It holds a 'treasure' object and the file object.
  *
  * You must implement 'newFunction', 'openFunction' and 'saveFunction'.
  * So ObjectKeeper can do the right thing to the treasure and file.
  *
  * The treasure can only be created by 'newTreasure' and 'open' function.
  * When opening a file, 'fileOpenedSignal' will be emitted. And when using
  * 'save' function to save the file 'fileSaveSignal' will be emitted.
  *
  * A new file (created by newTreasure function) must be saved via 'saveAs' function,
  * because there is no currentFile for it.
  *
  * You can also set the edited status by yourself.
  * When you do this 'statusChangedSignal' will be emitted.
  *
  */
trait ObjectKeeper[T] {

  /* Signals */

  /** the signal emitted when edited status changed. */
  val statusChangedSignal:SimpleSignal[Boolean] = new SimpleSignal[Boolean]("edited status changed")

  /** the signal emitted when a file opened. */
  val fileOpenedSignal:SimpleSignal[File] = new SimpleSignal[File]("file opened")

  /** the signal emitted when a file saved. */
  val fileSavedSignal:SimpleSignal[File] = new SimpleSignal[File]("file saved")

  /* Important variables and getters */

  protected var _treasure: Option[T] = None
  protected var _file: Option[File] = None

  /**
    * @return an Option of current treasure loaded.
    */
  def treasure: Option[T] = _treasure

  /** @return an Option of the file holding current treasure. */
  def currentFile: Option[File] = _file



  /* The Functions MUST be implemented. */

  /** Implement this to create a new empty treasure. */
  protected val newFunction:()=>T

  /** Implement this to handle the way to open a file and load the treasure. */
  protected val openFunction:(File)=>T

  /** Implement this to handle the way to save the treasure to a file. */
  protected val saveFunction:(T,File)=>Unit


  /* CORE FUNCTIONS */

  /** Create a new empty treasure using the implemented 'newFunction' function.
    * After create it, currentFile will be set to null.
    * And edited will be set to false.
    */
  def newTreasure():Unit = {
    _treasure = Some(newFunction.apply())
    _file = None
    edited = false
  }

  /**
    * Open a file then parse and load the treasure inside using 'openFunction'.
    *
    * 'currentFile' will be set to the opened file. 'edit' status will be set to
    * false automatically.
    *
    * @param file the file you wish to open
    */
  def open(file:File):Unit = {
    _treasure = Some(openFunction.apply(file))
    _file = Some(file)
    edited = false
    fileOpenedSignal.emit(currentFile.get)
  }

  def open(filename:String):Unit = open(new File(filename))

  /**
    * Save current treasure to 'currentFile' using 'saveFunction'.
    *
    * @throws IOException if there is no current file (such as a new treasure).
    */
  def save():Unit = {
    if (currentFile != null) {
      saveFunction.apply(treasure.get, currentFile.get)
      edited = false
      fileSavedSignal.emit(currentFile.get)
    }else{
      throw new IOException("You must use saveAs function to save a new file.")
    }
  }

  /**
    * Save the treasure to a new file and open the new file.
    *
    * Actually the new file is not being parsed again.
    * Only the 'currentFile' is set to new one.
    *
    * When this function applies, 'fileSavedSignal' and 'fileOpenedSignal'
    * will both be emitted.
    *
    * @param file new file you wish to save as.
    */
  def saveAs(file:File):Unit = {
    saveFunction.apply(treasure.get, file)
    fileSavedSignal.emit(file)
    _file = Some(file)
    fileOpenedSignal.emit(currentFile.get)
  }

  def saveAs(filename:String):Unit = saveAs(new File(filename))

  private var _edited = false

  /** the edited status */
  def edited: Boolean = _edited

  /**
    * Set the edited status by hand.
    *
    * If the new value equals to the old one,
    * 'statusChangedSignal' will NOT be emitted.
    *
    * @param v
    */
  def edited_=(v: Boolean): Unit = {
    if (_edited != v){
      statusChangedSignal.emit(v)
      _edited = v
    }
  }


  /* SLOTS */

  /** a slot that will set the edited status to TRUE, no matter what
    * it received.
    */
  val treasureEditedSlot:SimpleSlot[Any] = new SimpleSlot[Any] {
    override val mainFunction: (Any) => Unit = (x) => edited = true
  }

  /**
    * slot set the edited status to the passed variable.
    */
  val editedStatusSlot:SimpleSlot[Boolean] = new SimpleSlot[Boolean] {
    override val mainFunction: (Boolean) => Unit = (x) => edited = x
  }

  /**
    * slot create a new empty treasure.
    */
  val newTreasureSlot:SimpleSlot[Any] = new SimpleSlot[Any] {
    override val mainFunction: (Any) => Unit = (x) => newTreasure()
  }

  /** slot open a file */
  val openSlot:SimpleSlot[File] = new SimpleSlot[File] {
    override val mainFunction: (File) => Unit = (f) => open(f)
  }

  /** slot save current file */
  val saveSlot:SimpleSlot[Any] = new SimpleSlot[Any] {
    override val mainFunction: (Any) => Unit = (x) => save()
  }

  /** slot save treasure to a new file and open it. */
  val saveAsSlot:SimpleSlot[File] = new SimpleSlot[File] {
    override val mainFunction: (File) => Unit = (f) => saveAs(f)
  }
}

