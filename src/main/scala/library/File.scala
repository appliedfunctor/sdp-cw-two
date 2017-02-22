package library

import scala.io.Source

/**
  * Created by aworton on 16/02/17.
  */
object File {
  def getLines(file: String): List[String] = {
    val source = Source.fromFile(file)
    if(source.isEmpty) {
      throw new IllegalArgumentException("File either doesn't exist or is empty")
    }
    source.getLines().toList
  }

}
