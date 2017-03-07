package bc

import library.ByteCode.{getPopValue, getPopVirtualMachine}
import vm.VirtualMachine

/**
  * Created by aworton on 22/02/17.
  */
class iSub() extends ByteCode with ByteCodeValues{

  /** @see [[ByteCode.code]] */
  override val code: Byte = bytecode("isub")

  /**
    * Pop the top two values from the VirtualMachine stack, subtract the second from the
    * first and push the result
    * @param vm the virtual machine
    * @return the updated virtual machine
    */
  override def execute(vm: VirtualMachine): VirtualMachine = {
    val firstPop = vm.pop()
    val secondPop = getPopVirtualMachine(firstPop).pop()
    getPopVirtualMachine(secondPop).push(getPopValue(firstPop) - getPopValue(secondPop))
  }
}
