package bc

import library.ByteCode.{getPopValue, getPopVirtualMachine}
import vm.VirtualMachine

/**
  * Created by aworton on 22/02/17.
  */
class iNeg() extends ByteCode with ByteCodeValues{

  /** @see [[ByteCode.code]] */
  override val code: Byte = bytecode("ineg")

  /**
    * Pop the top value from the VirtualMachine stack, negate it and push the result
    * @param vm the virtual machine
    * @return the updated virtual machine
    */
  override def execute(vm: VirtualMachine): VirtualMachine = {
    val firstPop = vm.pop()
    getPopVirtualMachine(firstPop).push(-getPopValue(firstPop))
  }
}