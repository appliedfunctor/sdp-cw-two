package bc

/**
  * Created by aworton on 22/02/17.
  */
class ConcreteByteCodeFactory extends ByteCodeFactory with ByteCodeValues{


  def getClassName(name: String): String = name match{
    case n: String if(n(0) == 'i') => "bc." + n(0) + n.substring(1).capitalize
    case n: String => "bc." + n.capitalize
  }

  override def make(byte: Byte, args: Int*): ByteCode = {
    val name = bytecode.find(pair => pair._2 == byte).map(pair => pair._1).getOrElse("")
    testName(name)
    argsCheck(name, args:_*)
    args.toArray.asInstanceOf[AnyRef]
    if(name != "iconst") {
      Class.forName(getClassName(name)).getConstructors()(0).newInstance().asInstanceOf[ByteCode]
    } else {
      Class.forName(getClassName(name)).getConstructors()(0).newInstance(args(0).asInstanceOf[AnyRef]).asInstanceOf[ByteCode]
    }
  }

  private def testName(name: String) = {
    if (name == "") {
      throw new InvalidBytecodeException("Byte code not known")
    }
  }

  private def argsCheck(name: String, args: Int*) = {
    if (args.size > 1 && name == "iconst") {
      throw new InvalidBytecodeException("Too many arguments supplied")
    }
    if (args.size == 0 && name == "iconst"){
      throw new InvalidBytecodeException("No arguments supplied to iconst")
    }
  }
}
