package vm
import bc.ByteCode

/**
  * Created by aworton on 23/02/17.
  */
class ConcreteVirtualMachine extends VirtualMachine{

  private var stack: List[Int] = Nil

  /**
    * {@inheritDoc}
    */
  override def execute(bc: Vector[ByteCode]): VirtualMachine = {
    bc.foreach(_.execute(this))
    this
  }

  /**
    * {@inheritDoc}
    */
  override def executeOne(bc: Vector[ByteCode]): (Vector[ByteCode], VirtualMachine) = bc.toList match{
    case instruction :: tail => (tail.toVector, execute(Vector(instruction)))
    case Nil => (Vector(), this)
  }

  /**
    * {@inheritDoc}
    */
  override def push(value: Int): VirtualMachine = {
    stack = value :: stack
    this
  }

  /**
    * {@inheritDoc}
    */
  override def pop(): (Int, VirtualMachine) = stack match {
    case Nil => throw new MachineUnderflowException("Cannot pop from an empty stack")
    case head :: tail => {
      stack = tail.asInstanceOf[List[Int]]
      (head, this)
    }
  }

  override def state: Vector[Int] = stack.toVector
}
