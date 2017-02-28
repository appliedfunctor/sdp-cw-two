package bc

import library.ByteCode.{getPopValue, getPopVirtualMachine}
import vm.VirtualMachine

/**
  * Created by aworton on 22/02/17.
  */
class iMul() extends ByteCode with ByteCodeValues{
  override val code: Byte = bytecode("imul")

  override def execute(vm: VirtualMachine): VirtualMachine = {
    val firstPop = vm.pop()
    val secondPop = getPopVirtualMachine(firstPop).pop()
    getPopVirtualMachine(secondPop).push(getPopValue(firstPop) * getPopValue(secondPop))
  }
}