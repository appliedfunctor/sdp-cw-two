package bc

/**
  * Created by aworton on 22/02/17.
  */
class ConcreteByteCodeFactory extends ByteCodeFactory with ByteCodeValues{


  def getClassName(name: String): String = name match{
    case n: String if(n(0) == 'i') => n(0) + n.substring(1).capitalize
    case n: String => n.capitalize
  }

  override def make(byte: Byte, args: Int*): ByteCode = {
    val name = bytecode.find(pair => pair._2 == byte).map(pair => pair._1).getOrElse("")
    if(name == "") {
      throw new IllegalArgumentException("Byte code not known")
    }

    Class.forName(getClassName(name)).getConstructors()(0).newInstance(args).asInstanceOf[ByteCode]

  }
}