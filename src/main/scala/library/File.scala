package library

import vendor.InvalidInstructionFormatException

import scala.io.Source

/**
  * Created by aworton on 16/02/17.
  */
object File {
  def getLines(file: String): List[String] = {
    val source = Source.fromFile(file)
    val lines = source.getLines().toList
    if(lines.forall(_.isEmpty)) throw new InvalidInstructionFormatException("Instruction list is empty") else lines
  }

}
