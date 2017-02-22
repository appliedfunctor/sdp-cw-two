package bc
import vm.VirtualMachine

/**
  * Created by aworton on 22/02/17.
  */
case class iConst(num: Int) extends ByteCode with ByteCodeValues{
  override val code: Byte = bytecode("iconst")

  override def execute(vm: VirtualMachine): VirtualMachine = vm.push(num)
}
