package library

import vm.VirtualMachine

/**
  * Created by aworton on 22/02/17.
  */
object ByteCode {

  def getPopVirtualMachine(popMachineTuple: (Int, VirtualMachine)): VirtualMachine = {
    popMachineTuple._2
  }

  def getPopValue(popMachineTuple: (Int, VirtualMachine)): Int = {
    popMachineTuple._1
  }

}
