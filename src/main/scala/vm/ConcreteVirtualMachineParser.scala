package vm
import bc.{ByteCode, ByteCodeParser, ByteCodeValues}
import vendor.{ProgramParser, Instruction}

/**
  * Created by aworton on 23/02/17.
  */
class ConcreteVirtualMachineParser(programParser: ProgramParser, byteCodeParser: ByteCodeParser) extends VirtualMachineParser with ByteCodeValues{

  override def parse(file: String): Vector[ByteCode] = {
    val instructions = programParser.parse(file)
    val byteVector = parseInstructionsToByteVector(instructions)
    byteCodeParser.parse(byteVector)
  }

  override def parseString(str: String): Vector[ByteCode] = {
    val instructions = programParser.parseString(str)
    val byteVector = parseInstructionsToByteVector(instructions)
    byteCodeParser.parse(byteVector)
  }

  /**
    * map an input vector of insructions to an output vector of bytes
    * @param instructions the input instructions
    * @return a vector of bytes
    */
  def parseInstructionsToByteVector(instructions: Vector[Instruction]): Vector[Byte] = {
    instructions.flatMap(instruction => bytecode(instruction.name) :: intsToBytes(instruction.args.toList))
  }

  def intsToBytes(ints: List[Int]):List[Byte] = ints match {
    case Nil => Nil
    case 0 :: tail => intsToBytes(tail)
    case head :: tail => head.asInstanceOf[Byte] :: intsToBytes(tail)
  }

}
