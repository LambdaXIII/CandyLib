package candylib.functions

import java.io.File

/**
  * Created by a on 2017/4/19.
  *
  * A whole new version of [[FileNameTool]], which is already deprecated.
  *
  * @constructor init it with a file path string or a file object.
  */
class PathBox(file:File) {

  private var _path = file.getAbsolutePath

  def this(filename: String) = this(new File(filename))

  def absolutePath:String = _path
  def absolutePath_=(p:String): Unit = {
    val f = new File(p)
    _path = f.getAbsolutePath
  }

  def dir:String = _path.substring(0, _path.lastIndexOf(File.separator))
  def dir_=(d:String):Unit = {
    val input = d.trim
    val otherPart = _path.substring(_path.lastIndexOf(File.separator))
    val leftPart = if (input.endsWith(File.separator)) PathBox.leftSubString(input) else input
    val file = new File(leftPart + otherPart)
    _path = file.getAbsolutePath
  }

  def filename: String = if (_path.contains('.')) {
    _path.substring(_path.lastIndexOf(File.separator) + 1, _path.indexOf("."))
  } else {
    _path.substring(_path.lastIndexOf(File.separator) + 1)
  }

  def filename_=(f: String): Unit = {
    val left = PathBox.leftSubString(_path)
    val right = PathBox.rightSubString(_path)
    val ext = if (right.contains(".")) right.substring(right.lastIndexOf(".")) else ""
    _path = left + File.separator + f.trim + ext
  }

  def suffix: String = {
    val right = PathBox.rightSubString(_path)
    if (right.contains(".")) {
      right.substring(right.lastIndexOf(".") + 1)
    }else ""
  }

  def suffix_=(s: String): Unit = {
    val newSuffix = if (s.trim.startsWith(".")) s.trim else "." + s.trim

    val left = PathBox.leftSubString(_path)
    val right = PathBox.rightSubString(_path)

    val newRight = if (right.contains(".")){
      right.substring(0, right.lastIndexOf(".")) + newSuffix
    }else right + newSuffix

    _path = left + File.separator + newRight
  }

  def toFile(): File = new File(_path)

  override def toString: String = _path

}

object PathBox{
  /** @return Left part of the absolute path string, excluding separator ("/") */
  private def leftSubString(path: String):String = path.substring(0, path.lastIndexOf(File.separator))

  /** @return Right part of the absolute path string, excluding separator ("/") */
  private def rightSubString(path: String): String = path.substring(path.lastIndexOf(File.separator) + 1)

  def apply(file: File): PathBox = new PathBox(file)

  def apply(filename: String): PathBox = new PathBox(filename)
}
