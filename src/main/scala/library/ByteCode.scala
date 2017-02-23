package library

import bc.ByteCodeValues
import vm.VirtualMachine

/**
  * Created by aworton on 22/02/17.
  */
object ByteCode extends ByteCodeValues {

  def getPopVirtualMachine(popMachineTuple: (Int, VirtualMachine)): VirtualMachine = {
    popMachineTuple._2
  }

  def getPopValue(popMachineTuple: (Int, VirtualMachine)): Int = {
    popMachineTuple._1
  }

  def getNameFromByteCode(byte: Byte): String = {
    bytecode.find(pair => pair._2 == byte).map(pair => pair._1).getOrElse("")
  }
}
