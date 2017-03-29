package candylib.functions

import java.io.File

/**
  * Created by a on 2017/3/30.
  */
object FileFunctions {
  def expandDirs(files:Set[File]):Set[File] = {
    var files = Set[File]()
    files.foreach((f) => {
      if(f.exists()){
        if (f.isDirectory){
          files ++= expandDirs(f.listFiles().toSet)
        }else{
          files += f
        }
      }
    })
    files
  }

}
