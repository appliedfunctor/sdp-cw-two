package bc

import factory.VirtualMachineFactory
import org.scalatest.{BeforeAndAfter, FunSuite}

class PublicByteCodeFactorySuite extends FunSuite with ByteCodeValues with BeforeAndAfter{
  var bcf: ByteCodeFactory  = _

  before{
    bcf = VirtualMachineFactory.byteCodeFactory
  }

  test("[5] all bytecodes should be made by factory") {
    // Tests that each bytecode (modulo "iconst") can be made.
    for ((name, code) <- bytecode - "iconst") {
      val bc = bcf.make(code)
      assert(bc.code == code, "invalid bytecode value")
    }

    // Test the iconst bytecode
    val bc = bcf.make(bytecode("iconst"), 4)
    assert(bc.code == bytecode("iconst"))
  }

  test("[3] an invalid bytecode should throw an exception") {
    intercept[InvalidBytecodeException] {
      bcf.make(99)
    }
  }
  test("Should throw an exception when two or more ints are passed to iconst"){
    intercept[InvalidBytecodeException]{
      val bc = bcf.make(bytecode("iconst"), 4, 5)
    }
  }

  test("Should throw an exception when no arguments are passed to iconst"){
    intercept[InvalidBytecodeException]{
      val bc = bcf.make(bytecode("iconst"))
    }
  }
}
