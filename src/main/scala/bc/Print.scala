package bc

import vm.VirtualMachine
import library.ByteCode.{getPopValue, getPopVirtualMachine}

/**
  * Created by mmkeri on 22/02/2017.
  */
case class Print() extends ByteCode{
  override val code: Byte = "print".toByte

  override def execute(vm: VirtualMachine): VirtualMachine = {
    var valueVmTuple = vm.pop()
    println(getPopValue(valueVmTuple))
    getPopVirtualMachine(valueVmTuple)
  }
}
