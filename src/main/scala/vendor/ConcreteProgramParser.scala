package vendor

import library.File
import bc.{ByteCodeValues, InvalidBytecodeException}

/**
  * Created by aworton on 16/02/17.
  */
class ConcreteProgramParser extends ProgramParser with ByteCodeValues {
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

  /**
    * Verify that the supplied instructions are not empty
    * @param instructions supplied instructions as a list of strings
    */
  private def verifyInstructionsNotEmpty(instructions: List[String]): Unit = {
    if (instructions.forall(_.isEmpty)) {
      throw new InvalidInstructionFormatException("Instruction list is empty")
    }
  }

  /**
    * Check that instruction is valid (has length and isn't blank)
    * filter out empty instructions
    * extract arguments and produce instructions from data
    *
    * @param instructions a list of input instructions
    * @return a vector of instantiated instructions
    */
  private def processInstructions(instructions: List[String]): Vector[Instruction] = {
    verifyInstructionsNotEmpty(instructions)

    val instructionVector = instructions
        .filter(_ != "")
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

  /**
    * line to instruction and argument converter
    * @param line the input line as a string
    * @return a tuple containing a string for the instruction and a string for the argument
    */
  private def lineDivider(line: String): Tuple2[String, Option[String]] = {
    val dividedLine = line.split(" ")

    dividedLine match {
      case dl:Array[String] if dl.length == 1 => Tuple2(dl(0), None)
      case dl:Array[String] if dl.length == 2 => Tuple2(dl(0), Some(dl(1)))
      case dl:Array[String] if dl.length > 2 => throw new InvalidInstructionFormatException("Instruction has too many parameters")
    }
  }

  /**
    * Produce instructions from a pair of instruction, optional argument tuple
    * @param instructionArgumentPair is a tuple holding (instruction, Option[argument])
    * @return instruction
    */
  private def instructionMaker(instructionArgumentPair: Tuple2[String, Option[String]]):Instruction = {
    val instructionName = instructionArgumentPair._1
    var argument = Vector[Int](0)
    val instructionNameChecked = validateInstructionIsKnown(instructionName)
    if(instructionArgumentPair._2.isDefined){
        argument = Vector(tryToParseArgumentToInt(instructionArgumentPair._2.get))
    }
    new Instruction(instructionNameChecked, argument)
  }

  /**
    * attempt to parse argument to int, throw InvalidInstructionFormatException if fails
    * @param arg the string value to parse
    * @return the parsed int value
    */
  private def tryToParseArgumentToInt(arg: String): Int = {
    try {
      arg.toInt
    }
    catch{
      case e: NumberFormatException => throw new InvalidInstructionFormatException("Argument is not a valid number")
    }
  }

  /**
    * test that the supplied instruction is known
    * @param instruction the supplied instruction
    * @return
    */
  def validateInstructionIsKnown(instruction: String): String = {
      if(!bytecode.contains(instruction)) {
        throw new InvalidBytecodeException("String submitted is not a valid instruction")
      }
      instruction
  }

}
