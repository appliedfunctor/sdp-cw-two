package vendor

import java.io.FileNotFoundException

import factory.VirtualMachineFactory
import org.scalatest.FunSuite

class PublicVendorParserSuite extends FunSuite {
  val vp: ProgramParser = VirtualMachineFactory.vendorParser

  test("[3] vendor parser should parse a program string correctly") {
    val insts = vp.parseString("iconst 4\niconst 5\niadd\nprint")
    assert(insts.length == 4)
  }

  test("Should throw an InvalidInstructionFormatException if passed an empty string"){
    assertThrows[InvalidInstructionFormatException]{
      vp.parseString("")
    }
  }

  test("Should throw an InvalidInstructionFormatException if passed a string with 2 ints as an argument for iConst"){
    assertThrows[InvalidInstructionFormatException]{
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

  test("throws a FileNotFoundException when given an invalid file"){
    assertThrows[FileNotFoundException]{
      vp.parse("programs/p00-file-does-not-exist.vm")
    }
  }

  test("throws an InvalidInstructionFormatException when given an empty file/line"){
    assertThrows[InvalidInstructionFormatException]{
      vp.parse("programs/p07-blank-file.vm")
    }
  }

  test("throws an InvalidInstructionFormatException if argument is not a number"){
    assertThrows[InvalidInstructionFormatException]{
      vp.parse("programs/p08-argument-type-wrong.vm")
    }
  }

  test("throw an exception if more than one int argument is given"){
    assertThrows[InvalidInstructionFormatException]{
      vp.parse("programs/p06.vm")
    }

  }
}
