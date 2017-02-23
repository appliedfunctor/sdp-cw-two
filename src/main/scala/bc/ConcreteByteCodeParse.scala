package bc
import library.ByteCode.getNameFromByteCode

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
    if(bc.length == 0){
      throw new IllegalArgumentException
    }
    bytesToInstructions(bc.toList).toVector
  }

  def bytesToInstructions(bc: List[Byte]): List[ByteCode] = {
    val bcf = new ConcreteByteCodeFactory()
    bc.toList match {
      case Nil => Nil
      case instruction :: value :: tail if(instruction == bytecode("iconst")) => bcf.make(instruction, value.toInt) :: bytesToInstructions(tail)
      case instruction :: tail => bcf.make(instruction) :: bytesToInstructions(tail)
    }
  }
}
