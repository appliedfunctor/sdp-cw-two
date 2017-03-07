package bc
import vm.VirtualMachine

/**
  * Created by aworton on 22/02/17.
  */
class iConst(num: Int) extends ByteCode with ByteCodeValues{

  /**
    * {@inheritDoc}
    */
  override val code: Byte = bytecode("iconst")

  /**
    * Pushes num onto the vm stack
    * @param vm the virtual machine
    * @return the updated virtual machine
    */
  override def execute(vm: VirtualMachine): VirtualMachine = vm.push(num)
}
