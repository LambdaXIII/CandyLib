package candylib.functions

import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by Charlie on 2017/2/4.
  */
class FileNameTool$Test extends FlatSpec with Matchers {
  it should "init properly in different os." in {
    System.getProperty("os.name") match {
      case m: String if (m.toLowerCase().contains("windows")) =>
        val a = FileNameTool("d:\\q\\d\\a.b")
        a.pureDir should be("d:\\q\\d\\")
        a.pureName should be("a")
        a.pureExt should be(".b")
      case _ =>
        val a = FileNameTool("/d/q/d/a.b")
        a.pureDir should be("/d/q/d/")
        a.pureName should be("a")
        a.pureExt should be(".b")
    }
  }
}
