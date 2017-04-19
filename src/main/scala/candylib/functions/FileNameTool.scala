package candylib.functions
import java.io.File

@deprecated
class FileNameTool {
  var _pureDir:String = ""
  var _pureName:String = ""
  var _pureExt:String = ""

  def pureDir:String = _pureDir
  def pureDir_=(dir:String):Unit = {
    var path = dir.trim
    if (! path.endsWith(File.separator)){
      path += File.separator
    }
    _pureDir = path
  }

  def pureName:String = _pureName
  def pureName_=(name:String):Unit = {
    var s = name.trim
    if (s.endsWith(".")){
      s = s.substring(0, s.length - 1)
    }
    if (s.startsWith(File.separator)){
      s = s.substring(1)
    }
    s.replace(File.separator, "_")
    _pureName = s
  }

  def pureExt:String = _pureExt
  def pureExt_=(ext:String): Unit ={
    var s = ext.trim
    if (! s.startsWith(".")){
      s = "." + s
    }
    _pureExt = s
  }

  def totalPath:String = _pureDir + _pureName + _pureExt
  def totalPath_=(path:String): Unit ={
    val file = new File(path)
    pureDir = file.getParentFile.getAbsolutePath
    val name = file.getName
    pureName = name.substring(0, name.lastIndexOf("."))
    pureExt = name.substring(name.lastIndexOf("."))
  }

  def toFile :File = new File(totalPath)
}

@deprecated
object FileNameTool{
  def apply(filename:String):FileNameTool = new FileNameTool{
    totalPath = new File(filename).getAbsolutePath
  }
  
  def apply(file:File):FileNameTool = new FileNameTool{
    totalPath = file.getAbsolutePath
  }
  
  def changeExt(path:String, newExt:String):String = {
    val tool = apply(path)
    tool.pureExt = newExt
    tool.totalPath
  }
  
  def changeName(path:String, newName:String):String = {
    val tool = apply(path)
    tool.pureName = newName
    tool.totalPath
  }
  
  def changePath(path:String, newDir:String):String = {
    val tool = apply(path)
    tool.pureDir = newDir
    tool.totalPath
  }
  
  
}

