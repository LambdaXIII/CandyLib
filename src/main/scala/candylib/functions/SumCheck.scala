package candylib.functions

import java.io.File
import java.nio.file.{Files, Paths}
import java.security.MessageDigest
import javax.xml.bind.DatatypeConverter

/**
  * Created by a on 2017/3/30.
  */
class SumCheck(file:File) {
  val inputFile = file

  def md5: String ={
    val b = Files.readAllBytes(Paths.get(inputFile.getAbsolutePath))
    val hash = MessageDigest.getInstance("MD5").digest(b)
    DatatypeConverter.printHexBinary(hash).toUpperCase
  }

}

object SumCheck{
  def apply(file: File): SumCheck = new SumCheck(file)
}
