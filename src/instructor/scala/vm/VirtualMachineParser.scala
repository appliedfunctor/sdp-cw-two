package vm

import bc.ByteCode

/**
  * A `VirtualMachineParser` is used to parse a file of bytecode
  * instructions (as defined by [[vendor.ProgramParser]]). Note,
  * we will use the vendor's parser to parse a file and use the
  * adapter design pattern to write an adapter that will
  * translate a vector of [[vendor.Instruction]] into a
  * vector [[bc.ByteCode]].
  */
trait VirtualMachineParser {
  def parse(file: String): Vector[ByteCode]

  def parseString(str: String): Vector[ByteCode]
}
