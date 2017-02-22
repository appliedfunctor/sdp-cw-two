package bc

import library.ByteCode.{getPopValue, getPopVirtualMachine}
import vm.VirtualMachine

/**
  * Created by mmkeri on 22/02/2017.
  */
class iDup() extends ByteCode{
  override val code: Byte = "idup".toByte

  override def execute(vm: VirtualMachine): VirtualMachine = {
    val firstPop = vm.pop()

    vm.push(getPopValue(firstPop))
  }

}
