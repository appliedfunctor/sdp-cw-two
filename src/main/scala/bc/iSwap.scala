package bc

import library.ByteCode.{getPopValue, getPopVirtualMachine}
import vm.VirtualMachine

/**
  * Created by mmkeri on 22/02/2017.
  */
class iSwap() extends ByteCode with ByteCodeValues{
  override val code: Byte = bytecode("iswap")

  override def execute(vm: VirtualMachine): VirtualMachine = {
    val firstPop = vm.pop()
    val secondPop = getPopVirtualMachine(firstPop).pop()
    val firstPush = getPopVirtualMachine(secondPop).push(getPopValue(firstPop))
    val secondPush = firstPush.push(getPopValue(secondPop))
    secondPush
  }
}
