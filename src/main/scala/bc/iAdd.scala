package bc

import vm.VirtualMachine
import library.ByteCode.{getPopValue, getPopVirtualMachine}

/**
  * Created by aworton on 22/02/17.
  */
class iAdd() extends ByteCode with ByteCodeValues{

  /**
    * {@inheritDoc}
    */
  override val code: Byte = bytecode("iadd")

  /**
    * Pop the top two values from the VirtualMachine stack, sum them and push the result
    * @param vm the virtual machine
    * @return the updated virtual machine
    */
  override def execute(vm: VirtualMachine): VirtualMachine = {
    val firstPop = vm.pop()
    val secondPop = getPopVirtualMachine(firstPop).pop()
    getPopVirtualMachine(secondPop).push(getPopValue(firstPop) + getPopValue(secondPop))
  }
}
