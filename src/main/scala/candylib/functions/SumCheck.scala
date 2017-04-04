package candylib.functions

import java.io.File
import java.nio.file.{Files, Paths}
import java.security.MessageDigest
import javax.xml.bind.DatatypeConverter

/**
  * Created by a on 2017/3/30.
  */
object SumCheck {

  private def hexStr(array:Array[Byte]):String = DatatypeConverter.printHexBinary(array).toUpperCase

  private def md5_single(inputFile:File): Array[Byte] = {
    val b = Files.readAllBytes(Paths.get(inputFile.getAbsolutePath))
    MessageDigest.getInstance("MD5").digest(b)
  }

  def md5(fileList:Set[File]): Set[String] = {
    val files = FileFunctions.expandDirs(fileList)
    files.map((f) => hexStr(md5_single(f)))
  }

  def md5(file:File): String = if (file.isDirectory) "" else hexStr(md5_single(file))

  def md5(filename:String): String = md5(new File(filename))
}
