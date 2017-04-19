package candylib.functions

import java.io.File

/**
  * Created by a on 2017/4/19.
  */
class PathBox(file:File) {

  private var _path = file.getAbsolutePath

  def absolutePath:String = _path
  def absolutePath_=(p:String): Unit = {
    val f = new File(p)
    _path = f.getAbsolutePath
  }

  def dir:String = _path.substring(0, _path.lastIndexOf(File.separator))
  def dir_=(d:String):Unit = {
    val input = d.trim
    val otherPart = _path.substring(_path.lastIndexOf(File.separator))
    val leftPart = if (input.endsWith(File.separator)) input.substring(0, input.lastIndexOf(File.separator)) else input
    val file = new File(leftPart + otherPart)
    _path = file.getAbsolutePath
  }

  def filename: String = if (_path.contains('.')) {
    _path.substring(_path.lastIndexOf(File.separator) + 1, _path.indexOf("."))
  } else {
    _path.substring(_path.lastIndexOf(File.separator) + 1)
  }

  def filename_=(f: String): Unit = {

  }


}
