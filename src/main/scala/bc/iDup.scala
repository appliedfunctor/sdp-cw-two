package bc

import library.ByteCode.{getPopValue}
import vm.VirtualMachine

/**
  * Implements a concrete [[ByteCode]] Instruction
  * @author Matthew Keri
  * @author Alexander Worton
  */
class iDup() extends ByteCode with ByteCodeValues{

  /** @see [[ByteCode.code]] */
  override val code: Byte = bytecode("idup")

  /**
    * Pop the top value from the VirtualMachine stack and push the result twice
    * @param vm the virtual machine
    * @return the updated virtual machine
    */
  override def execute(vm: VirtualMachine): VirtualMachine = {
    val firstPop = vm.pop()
    vm.push(getPopValue(firstPop)).push(getPopValue(firstPop))
  }

}
