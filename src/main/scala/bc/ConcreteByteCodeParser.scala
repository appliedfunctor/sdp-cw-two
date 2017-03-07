package bc
import library.ByteCode.getNameFromByteCode
import vendor.InvalidInstructionFormatException

/**
  * Created by mmkeri on 23/02/2017.
  */
class ConcreteByteCodeParser extends ByteCodeValues with ByteCodeParser {
  /**
    * Parses a vector of `Byte` into a vector of `ByteCode`.
    *
    * You should use [[ByteCodeValues.bytecode]] to help translate
    * the individual `Byte`s into a correponding [[ByteCode]].
    *
    * @param bc a vector of bytes representing bytecodes
    * @return a vector of `ByteCode` objects
    */
  override def parse(bc: Vector[Byte]): Vector[ByteCode] = {
    var iconst = false
    validateVectorNotEmpty(bc)
    bytesToInstructions(bc.toList).toVector
  }

  /**
    * Perfroms recursive parsing of bytes into instructions (bytecode)
    * @param bc the list of bytes
    * @return the list of parsed bytecode instructions
    */
  def bytesToInstructions(bc: List[Byte]): List[ByteCode] = {
    val factory = new ConcreteByteCodeFactory()
    bc match {
      case Nil => Nil
      case instruction :: value :: tail if instruction == bytecode("iconst") => factory.make(instruction, value.toInt) :: bytesToInstructions(tail)
      case instruction :: tail => factory.make(instruction) :: bytesToInstructions(tail)
    }
  }

  /**
    * validate the supplied vector to ensure that it is not empty
    * @param bytes the supplied byte vector
    */
  private def validateVectorNotEmpty(bytes: Vector[Byte]): Unit = {
    if(bytes.isEmpty){
      throw new InvalidInstructionFormatException("Expected argument does not exist")
    }
  }
}
