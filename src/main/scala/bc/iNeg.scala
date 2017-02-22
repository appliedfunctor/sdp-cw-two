package bc

import library.ByteCode.{getPopValue, getPopVirtualMachine}
import vm.VirtualMachine

/**
  * Created by aworton on 22/02/17.
  */
case class iNeg() extends ByteCode{
  override val code: Byte = "ineg".toByte

  override def execute(vm: VirtualMachine): VirtualMachine = {
    val firstPop = vm.pop()

    getPopVirtualMachine(firstPop).push(-getPopValue(firstPop))
  }
}