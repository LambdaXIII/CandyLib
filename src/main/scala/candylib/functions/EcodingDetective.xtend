package candy

import java.io.File
import org.mozilla.intl.chardet.nsDetector
import org.mozilla.intl.chardet.nsICharsetDetectionObserver
import org.eclipse.xtend.lib.annotations.Accessors
import java.util.ArrayList
import java.io.BufferedInputStream
import java.io.FileInputStream

/**
 * EncodingDetective是Mozilla的自动探测库的封装。
 * 
 * 使用File或文件路径初始化之后，使用{@link guessEncoding}方法探测，
 * 返回一个字符串数组，根据可能性排列可能的编码。
 */
class EcodingDetective {
    @Accessors val File inputFile

    new(File f) {
        inputFile = f
    }

    new(String filename) {
        this(new File(filename))
    }

    private def String[] parse(nsDetector det) {
        val encoding = new ArrayList<String>(2)
        val found = new LogicKey()

        det.Init(new nsICharsetDetectionObserver() {
            override def Notify(String s) {
                encoding.add(s)
                found.setTrue()
            }
        })

        val imp = new BufferedInputStream(new FileInputStream(inputFile))
        val byte[] buf = newByteArrayOfSize(1024)
        var int len = 0
        var Boolean done = false
        var Boolean isAscii = false

        do {
            len = imp.read(buf, 0, buf.length)
            isAscii = det.isAscii(buf, len)
            done = det.DoIt(buf, len, false)
        } while ((len != -1) || isAscii || done)

        imp.close()
        det.DataEnd()

        if (isAscii) {
            #["ASCII"]
        } else {
            det.probableCharsets.toArray(#[""])
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
    def String[] guessEncoding(int languageHint) {
        parse(new nsDetector(languageHint))
    }

    /**无语言提示代码的情况下瞎猜 */
    def String[] guessEncoding() {
        parse(new nsDetector())
    }
}
