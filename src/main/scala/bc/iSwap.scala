package bc

import library.ByteCode.{getPopValue, getPopVirtualMachine}
import vm.VirtualMachine

/**
  * Created by mmkeri on 22/02/2017.
  */
class iSwap() extends ByteCode with ByteCodeValues{
  override val code: Byte = bytecode("iswap")

  /**
    * Pop the top two values from the VirtualMachine stack, push them back in reverse order
    * @param vm the virtual machine
    * @return the updated virtual machine
    */
  override def execute(vm: VirtualMachine): VirtualMachine = {
    val firstPop = vm.pop()
    val secondPop = getPopVirtualMachine(firstPop).pop()
    val firstPush = getPopVirtualMachine(secondPop).push(getPopValue(firstPop))
    val secondPush = firstPush.push(getPopValue(secondPop))
    secondPush
  }
}
