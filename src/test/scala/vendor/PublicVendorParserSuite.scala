package vendor

import factory.VirtualMachineFactory
import org.scalatest.FunSuite

class PublicVendorParserSuite extends FunSuite {
  val vp = VirtualMachineFactory.vendorParser

  test("[3] vendor parser should parse a program string correctly") {
    val insts = vp.parseString("iconst 4\niconst 5\niadd\nprint")
    assert(insts.length == 4)
  }

  test("Should throw an IllegalArgumentException if passed an empty string"){
    assertThrows[IllegalArgumentException]{
      vp.parseString("")
    }
  }

  test("Should throw an IllegalArgumentException if passed a string with 2 ints as an argument for iConst"){
    assertThrows[IllegalArgumentException]{
      vp.parseString("iconst 4 5\niconst 5 7\niadd\nprint")
    }
  }

  test("Should ignore a blank line in an instruction sequence"){
    val insts = vp.parseString("iconst 4\n\niconst 5\niadd\nprint")
    assert(insts.length == 4)
  }

  test("[4] vendor parser should parse a program file correctly") {
    val insts = vp.parse("programs/p03.vm")
    assert(insts.length == 20)

    val all = Vector("iconst", "iconst", "iswap", "iadd", "iconst", "iadd",
      "iconst", "isub", "iconst", "imul", "iconst", "idiv",
      "iconst", "irem", "ineg", "idec", "iinc", "idup", "print", "print")
    for (i <- insts.indices) {
      assert(insts(i).name == all(i))
    }
  }

  test("throws an IllegalArgumentException when given an empty file/line"){
    assertThrows[IllegalArgumentException]{
      vp.parse("programs/p07-blank-file.vm")
    }
  }

  test("throws an IllegalArgumentException if argument is not a number"){
    val caught = intercept[IllegalArgumentException]{
      vp.parse("programs/p08-argument-type-wrong.vm")
    }
    assert(caught.leftSide.toString == (new IllegalArgumentException).leftSide.toString)
  }

  test("throw an exception if more than one int argument is given"){
    assertThrows[IllegalArgumentException]{
      vp.parse("programs/p06.vm")
    }

  }
}
