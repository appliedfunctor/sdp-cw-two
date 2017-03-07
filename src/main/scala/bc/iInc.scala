package bc

import library.ByteCode.{getPopValue, getPopVirtualMachine}
import vm.VirtualMachine

/**
  * Created by mmkeri on 22/02/2017.
  */
class iInc() extends ByteCode with ByteCodeValues{
  override val code: Byte = bytecode("iinc")

  /**
    * Pop the top value from the VirtualMachine stack, increment it and push the result
    * @param vm the virtual machine
    * @return the updated virtual machine
    */
  override def execute(vm: VirtualMachine): VirtualMachine = {
    val firstPop = vm.pop()

    getPopVirtualMachine(firstPop).push(getPopValue(firstPop) + 1)
  }
}
