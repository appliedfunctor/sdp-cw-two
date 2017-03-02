package library

import java.io.FileNotFoundException

import org.scalatest.FunSuite
import vendor.InvalidInstructionFormatException

/**
  * Created by aworton on 16/02/17.
  */
class FileSuite extends FunSuite {

  test("unknown file raises a FileNotFoundException"){
    assertThrows[FileNotFoundException]{
      File.getLines("")
    }
  }

  test("empty file returns empty list"){
    assertThrows[InvalidInstructionFormatException]{
      File.getLines("programs/p07-blank-file.vm")
    }
  }

  test("p01.vm returns correct data"){
    val expected = "iconst 4" :: "iconst 5" :: "iadd" :: "print" :: Nil
    assert(expected == File.getLines("programs/p01.vm"))
  }

}
