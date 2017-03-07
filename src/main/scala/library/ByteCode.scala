package library

import bc.ByteCodeValues
import vm.VirtualMachine

/**
  * Created by aworton on 22/02/17.
  */
object ByteCode extends ByteCodeValues {

  /**
    * Retrieve and return the virtual machine from a pop tuple
    * @param popMachineTuple the tuple returned from a vm pop
    * @return the virtual machine component of the tuple
    */
  def getPopVirtualMachine(popMachineTuple: (Int, VirtualMachine)): VirtualMachine = {
    popMachineTuple._2
  }

  /**
    * Retrieve and return the value from a pop tuple
    * @param popMachineTuple the tuple returned from a vm pop
    * @return the value component of the tuple
    */
  def getPopValue(popMachineTuple: (Int, VirtualMachine)): Int = {
    popMachineTuple._1
  }

  /**
    * Get the name from a supplied byte
    * @param byte the provided byte
    * @return the name of the known bytecodevalue
    */
  def getNameFromByteCode(byte: Byte): String = {
    bytecode.find(pair => pair._2 == byte).map(pair => pair._1).getOrElse("")
  }
}
