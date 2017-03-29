package candylib.functions

import java.io.File

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

}
