package vm
import bc.{ByteCode, ByteCodeParser, ByteCodeValues}
import vendor.{Instruction, ProgramParser}

/**
  * Created by aworton on 23/02/17.
  */
class ConcreteVirtualMachineParser(programParser: ProgramParser, byteCodeParser: ByteCodeParser) extends VirtualMachineParser with ByteCodeValues {

  /**
    * Read the supplied file and convert all the instructions into a vector of ByteCode
    * @param file the supplied file
    * @return a parsed vector of ByteCode
    */
  override def parse(file: String): Vector[ByteCode] = {
    val instructions = programParser.parse(file)
    val alteredInstructions = removeZeros(instructions)
    val byteVector = parseInstructionsToByteVector(alteredInstructions)
    byteCodeParser.parse(byteVector)
  }

  /**
    * Read the supplied string and convert all the instructions into a vector of ByteCode
    * @param str the supplied instructions as a string
    * @return a parsed vector of ByteCode
    */
  override def parseString(str: String): Vector[ByteCode] = {
    val instructions = programParser.parseString(str)
    val alteredInstructions = removeZeros(instructions)
    val byteVector = parseInstructionsToByteVector(alteredInstructions)
    byteCodeParser.parse(byteVector)
  }

  /**
    * map the instructionList and removes any 0 arguments not associated with an iconst instruction
    * @param instructions Vector of Instructions
    * @return altered Vector[Instruction]
    */
  def removeZeros(instructions: Vector[Instruction]):Vector[Instruction] = {
    instructions.map(x => if(x.name != "iconst") { new Instruction(x.name, Vector[Int]())} else {new Instruction(x.name, x.args)})
  }

  /**
    * map an input vector of insructions to an output vector of bytes
    * @param instructions the input instructions
    * @return a vector of bytes
    */
  def parseInstructionsToByteVector(instructions: Vector[Instruction]): Vector[Byte] = {
    instructions.flatMap(instruction => bytecode(instruction.name) :: intsToBytes(instruction.args.toList))
  }

  /**
    * Parse each integer and convert to byte
    * @param ints the supplied list on integers
    * @return the processed byte list
    */
  private def intsToBytes(ints: List[Int]): List[Byte] = ints match {
    case Nil => Nil
    case head :: tail => validateAndConvertToByte(head) :: intsToBytes(tail)
  }

  /**
    * validate the supplied integer to check for overflow and underflow conditions
    * when converting from integer.
    * throw an ArgumentOverflowException if an overflow occurs
    * throw an ArgumentUnderflowException if an underflow occurs
    * @param argument the integer argument
    * @return the parsed byte
    */
  private def validateAndConvertToByte(argument: Int): Byte = argument match{
    case arg: Int if(arg > Byte.MaxValue) => throw new ArgumentOverflowException("Value greater than byte can hold. Overflow would occur")
    case arg: Int if(arg < Byte.MinValue) => throw new ArgumentUnderflowException("Value lower than byte can hold. Underflow would occur")
    case arg: Int => arg.asInstanceOf[Byte]
  }

}
//exception definition for argument overflow condition (int to byte)
class ArgumentOverflowException(message: String) extends Exception(message)
//exception definition for argument underflow condition (int to byte)
class ArgumentUnderflowException(message: String) extends Exception(message)
