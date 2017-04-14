package candylib.functions

import java.io._

import scala.collection.mutable

/**
  * Created by a on 2017/3/30.
  *
  * A bunch of pure functions to deal with [[java.io.File]] objects
  * or files on file system.
  *
  */
object FileFunctions {
  /**
    * Expand Dirs to a [[mutable.Set]] of [[java.io.File]] in place.
    * @param files a [[mutable.Set]] of [[java.io.File]].
    *              Some of the Files may be a Directory.
    * @return a Set of Files, all Directories in the input set will
    *         expand to a lot of files in place.
    */
  def expandDirs(files: Set[File]): Set[File] = {
    val expandedFiles = mutable.HashSet[File]()
    files.foreach((f) => {
      if (f.exists()) {
        if (f.isDirectory) {
          expandedFiles ++= expandDirs(f.listFiles().toSet)
        } else {
          expandedFiles += f
        }
      }
    })
    expandedFiles.toSet
  }

  /**
    * Calculate the size of a [[java.io.File]]
    * @param file the File
    * @return a Long Integer size of the file.
    *         Will be 0 if the file isn't exist.
    */
  def getFileSize(file: File): Long =
    if (file.exists()) {
      val inStream = new FileInputStream(file)
      inStream.available()
    } else 0

  /**
    * A quick function to save a String to a file.
    * @param outputFile Target File
    * @param content the String you want to save.
    * @param encoding the Encoding of the file.
    */
  def saveTextFile(outputFile: File, content: String, encoding: String = "UTF-8"): Unit = {
    if (!outputFile.exists()) outputFile.createNewFile()

    val writer = new BufferedWriter(
      new OutputStreamWriter(
        new FileOutputStream(outputFile),
        encoding
      )
    )

    writer.write(content)
    writer.flush()
    writer.close()
  }

  def saveTextFile(filename: String, content: String, encoding: String): Unit =
    saveTextFile(new File(filename), content, encoding)

}
