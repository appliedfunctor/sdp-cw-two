package bc

import library.ByteCode.{getPopValue, getPopVirtualMachine}
import vm.VirtualMachine

/**
  * Created by aworton on 22/02/17.
  */
case class iMul() extends ByteCode{
  override val code: Byte = "imul".toByte

  override def execute(vm: VirtualMachine): VirtualMachine = {
    val firstPop = vm.pop()
    val secondPop = getPopVirtualMachine(firstPop).pop()
    getPopVirtualMachine(secondPop).push(getPopValue(firstPop) * getPopValue(secondPop))
  }
}