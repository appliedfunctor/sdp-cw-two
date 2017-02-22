package bc

import vm.VirtualMachine
import library.ByteCode.{getPopValue, getPopVirtualMachine}

/**
  * Created by aworton on 22/02/17.
  */
case class iAdd() extends ByteCode{
  override val code: Byte = "iadd".toByte

  override def execute(vm: VirtualMachine): VirtualMachine = {
    val firstPop = vm.pop()
    val secondPop = getPopVirtualMachine(firstPop).pop()
    getPopVirtualMachine(secondPop).push(getPopValue(firstPop) + getPopValue(secondPop))
  }
}
