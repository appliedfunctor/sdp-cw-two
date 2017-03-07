package vm
import bc.ByteCode

/**
  * Created by aworton on 23/02/17.
  */
class ConcreteVirtualMachine extends VirtualMachine{

  private var stack: List[Int] = Nil

  /**
    * Execute the sequence of ByteCode by calling the execute method on each in turn
    * @param bc the supplied vector of ByteCode (instructions)
    * @return the resultant virtual machine after execution
    */
  override def execute(bc: Vector[ByteCode]): VirtualMachine = {
    bc.foreach(_.execute(this))
    this
  }

  /**
    * Execute the first instruction only in the supplied sequence of ByteCode
    * @param bc the supplied vector of ByteCode (instructions)
    * @return a tuple of remaining bytecode and the resultant virtual machine after execution
    */
  override def executeOne(bc: Vector[ByteCode]): (Vector[ByteCode], VirtualMachine) = bc.toList match{
    case instruction :: tail => (tail.toVector, execute(Vector(instruction)))
    case Nil => (Vector(), this)
  }

  /**
    * Push the supplied value onto the virtual machine stack
    * @param value the supplied integer value
    * @return the resuktant virtual machine
    */
  override def push(value: Int): VirtualMachine = {
    stack = value :: stack
    this
  }

  /**
    * Pop the value at the top of the virtual machine stack and return it and the resultant
    * virtual machine
    * @return a tuple holding the value and the resultant virtual machine
    */
  override def pop(): (Int, VirtualMachine) = stack match {
    case Nil => throw new MachineUnderflowException("Cannot pop from an empty stack")
    case head :: tail => {
      stack = tail.asInstanceOf[List[Int]]
      (head, this)
    }
  }

  /**
    * Get the stack as a vector of ints
    * @return the stack
    */
  override def state: Vector[Int] = stack.toVector
}
