package bc
import vm.VirtualMachine

/**
  * Created by aworton on 22/02/17.
  */
case class iConst(num: Int) extends ByteCode{
  override val code: Byte = "iconst".toByte

  override def execute(vm: VirtualMachine): VirtualMachine = vm.push(num)
}
