package library

import vendor.InvalidInstructionFormatException

import scala.io.Source

/**
  * A helper class to provide methods relating to File operations.
  * @author Matthew Keri
  * @author Alexander Worton
  */
object File {

  /**
    * Retrieve the lines from a file located at the supplied path
    * @param file the path to a file
    * @return a sequence of lines from the file
    */
  def getLines(file: String): List[String] = {
    val source = Source.fromFile(file)
    val lines = source.getLines().toList
    if(lines.forall(_.isEmpty)) throw new InvalidInstructionFormatException("Instruction list is empty") else lines
  }

}
