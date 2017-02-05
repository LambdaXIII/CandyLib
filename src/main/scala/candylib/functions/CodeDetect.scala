package candylib.functions

import java.io.{BufferedInputStream, File, FileInputStream}

import org.mozilla.intl.chardet.nsDetector
import org.mozilla.intl.chardet.nsICharsetDetectionObserver

import scala.collection.mutable.ArrayBuffer
import scala.util.control.Breaks.{breakable,break}

class CodeDetect(file:File) {
  val inputFile = file
  
  private def parse(detector:nsDetector):Seq[String] = {
    val encodings = ArrayBuffer[String]()
    var found = false
    detector.Init(new nsICharsetDetectionObserver{
      override def Notify(s:String) = {
        encodings += s
        found = true
      }
    })

    val imp = new BufferedInputStream(new FileInputStream((inputFile)))
    val buf = new Array[Byte](1024)
    var len:Int = 0
    //var done:Boolean = false
    var isASCII:Boolean = false
    
/*    不知道为什么
    这段代码不管用的*/
    /*
    do {
      len = imp.read(buf, 0, buf.length)
      isASCII = detector.isAscii(buf, len)
      done = detector.DoIt(buf, len, false)
    }while((len != -1) || isASCII || done)
*/
    breakable{
      do {
        len = imp.read(buf, 0, buf.length)
        isASCII = detector.isAscii(buf,len)
        if (isASCII) break
        if (detector.DoIt(buf, len, false)) break
      }while(len != -1)
    }
    imp.close()
    detector.DataEnd()

    if (isASCII){
      Seq[String]("ASCII")
    }else{
      detector.getProbableCharsets.toSeq
    }
  }

  /**
    * 猜测文本文件编码方式
    *
    * @param languageHint 地区语言提示代码，取值如下
    *                    - 1 : Japanese
    *                    - 2 : Chinese
    *                    - 3 : Simplified Chinese
    *                    - 4 : Traditional Chinese
    *                    - 5 : Korean
    *                    - 6 : Don't know(default)
    * @return 猜测的编码的序列，按照可能性排列
    */
  def guessEncoding(languageHint:Int):Seq[String] = {
    parse(new nsDetector(languageHint))
  }

  def guessEncoding():Seq[String] = parse(new nsDetector())

}

object CodeDetect{
  def apply(file: File): CodeDetect = new CodeDetect(file)
  def apply(filename:String):CodeDetect = new CodeDetect(new File(filename))
  def quickGuess(file:File):String = {
    new CodeDetect(file).guessEncoding()(0)
  }
}