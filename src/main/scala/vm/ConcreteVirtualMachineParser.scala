package vm
import bc.{ByteCode, ByteCodeParser, ByteCodeValues}
import vendor.{Instruction, ProgramParser}

/**
  * Created by aworton on 23/02/17.
  */
class ConcreteVirtualMachineParser(programParser: ProgramParser, byteCodeParser: ByteCodeParser) extends VirtualMachineParser with ByteCodeValues {

  override def parse(file: String): Vector[ByteCode] = {
    val instructions = programParser.parse(file)
    val alteredInstructions = removeZeros(instructions)//added code. Untested yet
    val byteVector = parseInstructionsToByteVector(alteredInstructions)
    byteCodeParser.parse(byteVector)
  }

  override def parseString(str: String): Vector[ByteCode] = {
    val instructions = programParser.parseString(str)
    val alteredInstructions = removeZeros(instructions)//added code. Untested yet
    val byteVector = parseInstructionsToByteVector(alteredInstructions)
    byteCodeParser.parse(byteVector)
  }

  def removeZeros(instructions: Vector[Instruction]):Vector[Instruction] = {
    instructions.map(x => if(x.name != "iconst") { new Instruction(x.name, Vector[Int]())} else {new Instruction(x.name, x.args)})
    //instructions.map(x => (if x._1 != "iconst") {x._1 +: Vector[Int]()})
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
    //case 0 :: tail => intsToBytes(tail)
    case head :: tail => head.asInstanceOf[Byte] :: intsToBytes(tail)
  }

}
