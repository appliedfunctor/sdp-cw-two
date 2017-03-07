package bc

import vm.VirtualMachine
import library.ByteCode.{getPopValue, getPopVirtualMachine}
import library.Io

/**
  * Created by mmkeri on 22/02/2017.
  */
class Print() extends ByteCode{

  /**
    * {@inheritDoc}
    */
  override val code: Byte = bytecode("print")

  /**
    * Pop the top value from the VirtualMachine stack and print it
    * @param vm the virtual machine
    * @return the updated virtual machine
    */
  override def execute(vm: VirtualMachine): VirtualMachine = {
    val valueVmTuple = vm.pop()
    Io.output(getPopValue(valueVmTuple))
    getPopVirtualMachine(valueVmTuple)
  }
}
