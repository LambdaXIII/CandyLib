

object CsvCode {

  def encodeLine(l: Seq[Any], separator:String=",", newLine:String="\n"): String = l.map(i => i.toString).mkString(separator) + newLine

  def encodeLines(t: Seq[Seq[Any]], separator:String=",", newLine:String="\n"): Seq[String] = t.map(l => encodeLine(l))

  def decodeLine(l: String, separator:String=","): Seq[String] = l.trim.split(separator).map(i => i.trim)

  def decodeLines(t: String, separator: String=",", newLine: String = "\n"):Seq[Seq[String]] = t.split(newLine).map(l => decodeLine(l))

}
