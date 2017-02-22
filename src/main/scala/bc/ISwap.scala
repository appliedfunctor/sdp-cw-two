package bc

import library.ByteCode.{getPopValue, getPopVirtualMachine}
import vm.VirtualMachine

/**
  * Created by mmkeri on 22/02/2017.
  */
class ISwap() extends ByteCode{
  override val code: Byte = "iswap".toByte

  override def execute(vm: VirtualMachine): VirtualMachine = {
    val firstPop = vm.pop()
    val secondPop = getPopVirtualMachine(firstPop).pop()
    getPopVirtualMachine(secondPop).push(getPopValue(firstPop)).push(getPopValue(secondPop))
  }
}
