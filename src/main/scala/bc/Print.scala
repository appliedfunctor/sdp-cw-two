package bc

import vm.VirtualMachine
import library.ByteCode.{getPopValue, getPopVirtualMachine}
import library.Io

/**
  * Implements a concrete [[ByteCode]] Instruction
  * @author Matthew Keri
  * @author Alexander Worton
  */
class Print() extends ByteCode{

  /** @see [[ByteCode.code]] */
  override val code: Byte = bytecode("print")

  /**
    * Pop the top value from the VirtualMachine stack and print it
    * @param vm the virtual machine
    * @return the updated virtual machine
    */
  override def execute(vm: VirtualMachine): VirtualMachine = {
    val valueVmTuple = vm.pop()
    Io.output(getPopValue(valueVmTuple))
    getPopVirtualMachine(valueVmTuple)
  }
}
