package vendor

import library.{File}

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
    val instructions = File.getLines(file)
    processInstructions(instructions)
  }

  private def processInstructions(instructions: List[String]): Vector[Instruction] = {
    if (instructions.length == 1 && instructions(0) == "") {
      throw new IllegalArgumentException
    }

    val instructionVector = instructions
        .filter(line => line != "")
        .map(line => {
          val arguments = lineDivider(line)
          instructionMaker(arguments)
        }).toVector
    instructionVector
  }

  /**
    * Parses a string representation of a bytecode program
    * into an `InstructionList`.
    *
    * @param string the string to parse
    * @return an instruction list
    */
  override def parseString(string: String): InstructionList = {
    val instructions = string.split("\n").toList
    val instructionVectors = processInstructions(instructions)
    instructionVectors
  }

  def lineDivider(line: String): Tuple2[String, String] = {

    val dividedLine = line.split(" ")

    dividedLine match {
      case dl:Array[String] if(dl.length == 1) => Tuple2(dl(0), " ")
      case dl:Array[String] if(dl.length == 2) => Tuple2(dl(0), dl(1))
      case dl:Array[String] if(dl.length > 2) => throw new IllegalArgumentException
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
