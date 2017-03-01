package vm

import factory.VirtualMachineFactory
import org.scalatest.{BeforeAndAfter, FunSuite}

class PublicVirtualMachineSuite extends FunSuite with BeforeAndAfter {

    val vmp = VirtualMachineFactory.virtualMachineParser
    val bcp = VirtualMachineFactory.byteCodeParser
    val vm = VirtualMachineFactory.virtualMachine


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

  test("[2] iDec should work correctly"){
    val bc  = vmp.parseString("iconst 10\nidec")
    var next = vm.executeOne(bc)
    assert(next._2.state.head == 10)
    next = next._2.executeOne(next._1)
    assert(next._2.state.head == 9)
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

  test("[2] iNeg should work correctly"){
    val bc = vmp.parseString("iconst 5\nineg")
    var next = vm.executeOne(bc)
    assert(next._2.state.head == 5)
    next = next._2.executeOne(next._1)
    assert(next._2.state.head == -5)
  }

  test("[2] iRem should work correctly"){

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

  test("[2] iPrint should work correctly"){

  }
}
