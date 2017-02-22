package bc

import library.ByteCode.{getPopValue, getPopVirtualMachine}
import vm.VirtualMachine

/**
  * Created by mmkeri on 22/02/2017.
  */
case class iDec() extends ByteCode with ByteCodeValues{
  override val code: Byte = bytecode("idec")

  override def execute(vm: VirtualMachine): VirtualMachine = {
    val firstPop = vm.pop()

    getPopVirtualMachine(firstPop).push(getPopValue(firstPop) - 1)
  }
}
