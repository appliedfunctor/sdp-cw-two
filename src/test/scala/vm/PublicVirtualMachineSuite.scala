package vm

import bc.ByteCodeParser
import factory.VirtualMachineFactory
import library.Io
import org.scalatest.{BeforeAndAfter, FunSuite}

class PublicVirtualMachineSuite extends FunSuite with BeforeAndAfter {
  var bcp: ByteCodeParser  = _
  val vmp: VirtualMachineParser = VirtualMachineFactory.virtualMachineParser
  var vm: VirtualMachine = _

  before{
    bcp = VirtualMachineFactory.byteCodeParser
    vm = VirtualMachineFactory.virtualMachine
  }

  test("[10] a virtual machine should execute a program") {
    val bc  = vmp.parse("programs/p05.vm")
    val vm2 = vm.execute(bc)
  }

  test("[2] iadd should work correctly") {
    val bc  = vmp.parseString("iconst 1\niconst 2\niadd")
    var next = vm.executeOne(bc)
    assert(next._2.state.head == 1)
    next = next._2.executeOne(next._1)
    assert(next._2.state.head == 2)
    next = next._2.executeOne(next._1)
    assert(next._2.state.head == 3)
  }

  test("[2] iconst should work correctly") {
    val bc  = vmp.parseString("iconst 1")
    val (bc2, vm2) = vm.executeOne(bc)
    assert(vm2.state.head == 1)
  }

  test("[2] iconst should work correctly with zero") {
    val bc  = vmp.parseString("iconst 0")
    val (bc2, vm2) = vm.executeOne(bc)
    assert(vm2.state.head == 0)
  }

  test("[2] iconst should work for largest value byte holds") {
    val bc  = vmp.parseString("iconst " + Byte.MaxValue)
    val (bc2, vm2) = vm.executeOne(bc)
    assert(vm2.state.head == 127)
  }

  test("[2] iconst should throw ArgumentOverflowException with argument larger than byte holds") {
    val bc  = vmp.parseString("iconst " + (Byte.MaxValue + 1))
    assertThrows[ArgumentOverflowException]{
      vm.execute(bc)
    }
  }

  test("[2] iconst should work for smallest value byte holds") {
    val bc  = vmp.parseString("iconst " + Byte.MinValue)
    val (bc2, vm2) = vm.executeOne(bc)
    assert(vm2.state.head == Byte.MinValue)
  }

  test("[2] iconst should throw ArgumentUnderflowException with argument smaller than byte holds") {
    val bc  = vmp.parseString("iconst " + (Byte.MinValue - 1))
    assertThrows[ArgumentUnderflowException]{
      vm.execute(bc)
    }
  }

  test("iconst shoud work correctly with zero as part of a sequence of instructions"){
    val bc  = vmp.parseString("iconst 5\niconst 0")
    var next = vm.executeOne(bc)
    assert(next._2.state.head == 5)
    next = next._2.executeOne(next._1)
    assert(next._2.state.head == 0)
  }

  test("iconst should work correctly with zero as part of a reverse sequence of instructions"){
    val bc  = vmp.parseString("iconst 0\niconst 5")
    var next = vm.executeOne(bc)
    assert(next._2.state.head == 0)
    next = next._2.executeOne(next._1)
    assert(next._2.state.head == 5)
  }

  test("[2] iDec should work correctly"){
    val bc  = vmp.parseString("iconst 10\nidec")
    var next = vm.executeOne(bc)
    assert(next._2.state.head == 10)
    next = next._2.executeOne(next._1)
    assert(next._2.state.head == 9)
  }

  test("[2] iDec should work correctlypassing the 0 boundary"){
    val bc  = vmp.parseString("iconst 1\nidec\nidec")
    var next = vm.executeOne(bc)
    assert(next._2.state.head == 1)
    next = next._2.executeOne(next._1)
    assert(next._2.state.head == 0)
    next = next._2.executeOne(next._1)
    assert(next._2.state.head == -1)
  }

  test("[2] iDiv should work correctly"){
    val bc  = vmp.parseString("iconst 10\niconst 50\nidiv")
    var next = vm.executeOne(bc)
    assert(next._2.state.head == 10)
    next = next._2.executeOne(next._1)
    assert(next._2.state.head == 50)
    next = next._2.executeOne(next._1)
    assert(next._2.state.head == 5)
  }

  test("[2] iDiv throws ArithmeticException on divide by zero"){
    val bc  = vmp.parseString("iconst 1\nidec\niconst 50\nidiv")
    var next = vm.executeOne(bc)
    assert(next._2.state.head == 1)
    next = next._2.executeOne(next._1)
    assert(next._2.state.head == 0)
    next = next._2.executeOne(next._1)
    assert(next._2.state.head == 50)

    assertThrows[ArithmeticException]{
      next = next._2.executeOne(next._1)
    }
  }

  test("[2] iDup should work correctly"){
    val bc = vmp.parseString("iconst 5\nidup")
    var next = vm.executeOne(bc)
    assert(next._2.state.head == 5)
    val stateSize = next._2.state.size
    next = next._2.executeOne(next._1)
    assert(next._2.state.head == 5)
    assert(next._2.state.size == stateSize + 1)
  }

  test("[2] iInc should work correctly"){
    val bc = vmp.parseString("iconst 5\niinc")
    var next = vm.executeOne(bc)
    assert(next._2.state.head == 5)
    next = next._2.executeOne(next._1)
    assert(next._2.state.head == 6)
  }

  test("[2] iMul should work correctly"){
    val bc = vmp.parseString("iconst 5\niconst 2\nimul")
    var next = vm.executeOne(bc)
    assert(next._2.state.head == 5)
    next = next._2.executeOne(next._1)
    assert(next._2.state.head == 2)
    next = next._2.executeOne(next._1)
    assert(next._2.state.head == 10)
  }

  test("[2] iNeg should work correctly from pos to neg"){
    val bc = vmp.parseString("iconst 5\nineg")
    var next = vm.executeOne(bc)
    assert(next._2.state.head == 5)
    next = next._2.executeOne(next._1)
    assert(next._2.state.head == -5)
  }

  test("[2] iNeg should work correctly from neg to pos"){
    val bc = vmp.parseString("iconst -5\nineg")
    var next = vm.executeOne(bc)
    assert(next._2.state.head == -5)
    next = next._2.executeOne(next._1)
    assert(next._2.state.head == 5)
  }

  test("[2] iRem should work correctly"){
    val bc = vmp.parseString("iconst 2\niconst 5\nirem")
    var next = vm.executeOne(bc)
    assert(next._2.state.head == 2)
    next = next._2.executeOne(next._1)
    assert(next._2.state.head == 5)
    next = next._2.executeOne(next._1)
    assert(next._2.state.head == 1)
  }

  test("[2] isub should work correctly") {
    val bc  = vmp.parseString("iconst 1\niconst 2\nisub")
    var next = vm.executeOne(bc)
    assert(next._2.state.head == 1)
    next = next._2.executeOne(next._1)
    assert(next._2.state.head == 2)
    next = next._2.executeOne(next._1)
    assert(next._2.state.head == 1)
  }

  test("[2] iswap should work correctly") {
    val bc  = vmp.parseString("iconst 1\niconst 2\niswap")
    var next = vm.executeOne(bc)
    assert(next._2.state.head == 1)
    next = next._2.executeOne(next._1)
    assert(next._2.state.head == 2)
    next = next._2.executeOne(next._1)
    assert(next._2.state(0) == 1)
    assert(next._2.state(1) == 2)
  }

  test("[2] Print should work correctly"){
    val bc  = vmp.parseString("iconst 1\nprint")
    var next = vm.execute(bc)
    assert(Io.lastOutput == 1)
  }

  test("[2] executeOne doesn't change state and returns the vm"){
    val input = Vector()
    var next = vm.executeOne(input)
    assert(next._2 == vm)
    assert(next._1 == input)
  }

  test("[2] attempting tpo pop from an empty stack should throw a MachineUnderflowException"){
    val bc  = vmp.parseString("iswap")
    assertThrows[MachineUnderflowException]{
      vm.executeOne(bc)
    }
  }
}
