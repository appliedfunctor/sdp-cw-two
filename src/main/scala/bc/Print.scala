package bc

import vm.VirtualMachine
import library.ByteCode.{getPopValue, getPopVirtualMachine}
import library.Io

/**
  * Created by mmkeri on 22/02/2017.
  */
class Print() extends ByteCode{
  override val code: Byte = bytecode("print")

  override def execute(vm: VirtualMachine): VirtualMachine = {
    var valueVmTuple = vm.pop()
    Io.output(getPopValue(valueVmTuple))
    getPopVirtualMachine(valueVmTuple)
  }
}
