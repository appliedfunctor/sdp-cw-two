package bc

import library.ByteCode.{getPopValue}
import vm.VirtualMachine

/**
  * Created by mmkeri on 22/02/2017.
  */
class iDup() extends ByteCode with ByteCodeValues{
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
