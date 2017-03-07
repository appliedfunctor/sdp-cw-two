package bc
import library.ByteCode.getNameFromByteCode
/**
  * Implements a concrete [[ByteCodeFactory]]
  * @author Matthew Keri
  * @author Alexander Worton
  */
class ConcreteByteCodeFactory extends ByteCodeFactory with ByteCodeValues{

  private final val IConst = "iconst"

  /**
    * Perform capitalisation on the name. If the instruction begins with i, capitalise the second letter
    * otherwise capitalise the whole name.
    * @param name the input name
    * @return
    */
  def getClassName(name: String): String = name match{
    case n: String if(n(0) == 'i') => "bc." + n(0) + n.substring(1).capitalize
    case n: String => "bc." + n.capitalize
  }

  /** @see [[ByteCodeFactory.make]] */
  override def make(byte: Byte, args: Int*): ByteCode = {
    val name = getNameFromByteCode(byte)
    testName(name)
    argsCheck(name, args:_*)
    args.toArray.asInstanceOf[AnyRef]
    instantiateByteCode(name, args:_*)
  }

  /**
    * Instantiates and returns the instantiation of a ByteCode
    * @param name the name of the ByteCode
    * @param args the arguments for the instruction
    * @return the istantiated bytecode
    */
  private def instantiateByteCode(name: String, args: Int*): ByteCode = {
    if(name != IConst) {
      Class.forName(getClassName(name)).getConstructors()(0).newInstance().asInstanceOf[ByteCode]
    } else {
      Class.forName(getClassName(name)).getConstructors()(0).newInstance(args(0).asInstanceOf[AnyRef]).asInstanceOf[ByteCode]
    }
  }

  /**
    * validate that name is not empty
    * throw an InvalidBytecodeException if it is
    * @param name the supplied instruction name
    */
  private def testName(name: String) = {
    if (name.isEmpty) {
      throw new InvalidBytecodeException("Byte code not known")
    }
  }

  /**
    * Validate arguments. Ensure that only iconst instructions have arguments
    * throw InvalidBytecodeException if this is not the case
    * @param name the supplied instruction name
    * @param args the supplied arguments
    */
  private def argsCheck(name: String, args: Int*) = {
    if (args.size > 1 && name == IConst) {
      throw new InvalidBytecodeException("Too many arguments supplied")
    }
    if (args.size == 0 && name == IConst){
      throw new InvalidBytecodeException("No arguments supplied to iconst")
    }
  }
}
