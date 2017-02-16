package vendor

import library.{File, LineDivider}

/**
  * Created by aworton on 16/02/17.
  */
class vendorParser extends ProgramParser{
  /**
    * Parses a file representation of a bytecode program
    * into an `InstructionList`.
    *
    * @param file the file to parse
    * @return an instruction list
    */
  override def parse(file: String): InstructionList = {
    File.getLines(file).map(line => {
      val arguments = lineDivider(line)
      instructionMaker(arguments)
    }).toVector
  }

  /**
    * Parses a string representation of a bytecode program
    * into an `InstructionList`.
    *
    * @param string the string to parse
    * @return an instruction list
    */
  override def parseString(string: String): InstructionList = {
    Vector()
  }

  def lineDivider(line: String): Tuple2[String, String] = {

    val dividedLine = line.split(" ")
    if(dividedLine.length == 1){
      Tuple2(dividedLine(0), " ")
    } else if (dividedLine.length == 2){
      Tuple2(dividedLine(0), dividedLine(1))
    } else {
      throw new IllegalArgumentException
    }
  }

  def instructionMaker(args: Tuple2[String, String]):Instruction = {
    if(args._2 == " ")
    {
      new Instruction((args._1), Vector[Int](0))
    } else {
      new Instruction(args._1, Vector(args._2.toInt))
    }
  }
}
