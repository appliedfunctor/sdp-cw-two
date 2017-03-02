package factory

import bc.{ByteCodeFactory, ByteCodeParser, ConcreteByteCodeFactory, ConcreteByteCodeParser}
import vendor.{ConcreteProgramParser, ProgramParser}
import vm.{ConcreteVirtualMachine, ConcreteVirtualMachineParser, VirtualMachine, VirtualMachineParser}

/**
  * The `VirtualMachineFactory` follows the *factory pattern*. It provides
  * methods for each of the important parts in this assignment. You must
  * implement each method such that it returns an object of the correct type.
  */
object VirtualMachineFactory {
  def byteCodeFactory: ByteCodeFactory = new ConcreteByteCodeFactory

  def vendorParser: ProgramParser = new ConcreteProgramParser

  def byteCodeParser: ByteCodeParser = new ConcreteByteCodeParser

  def virtualMachineParser: VirtualMachineParser = new ConcreteVirtualMachineParser(vendorParser, byteCodeParser)

  def virtualMachine: VirtualMachine = new ConcreteVirtualMachine
}