package bc

import vm.VirtualMachine
import library.ByteCode.{getPopValue, getPopVirtualMachine}

/**
  * Created by aworton on 22/02/17.
  */
class iAdd() extends ByteCode with ByteCodeValues{
  override val code: Byte = bytecode("iadd")

  override def execute(vm: VirtualMachine): VirtualMachine = {
    val firstPop = vm.pop()
    val secondPop = getPopVirtualMachine(firstPop).pop()
    getPopVirtualMachine(secondPop).push(getPopValue(firstPop) + getPopValue(secondPop))
  }
}
