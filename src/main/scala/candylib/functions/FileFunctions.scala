package candylib.functions

import java.io._

import scala.collection.mutable

/**
  * Created by a on 2017/3/30.
  */
object FileFunctions {
  def expandDirs(files:Set[File]):Set[File] = {
    val expandedFiles = mutable.HashSet[File]()
    files.foreach((f) => {
      if(f.exists()){
        if (f.isDirectory){
          expandedFiles ++= expandDirs(f.listFiles().toSet)
        }else{
          expandedFiles += f
        }
      }
    })
    expandedFiles.toSet
  }

  def getFileSize(file:File): Long =
    if (file.exists()){
      val inStream = new FileInputStream(file)
      inStream.available()
    }else 0

  def saveTextFile(outputFile:File, content:String, encoding:String = "UTF-8"): Unit ={
    if (! outputFile.exists()) outputFile.createNewFile()

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

  def saveTextFile(filename:String, content:String, encoding:String):Unit =
    saveTextFile(new File(filename), content, encoding)

}
