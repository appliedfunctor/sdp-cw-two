package vm
import bc.ByteCode

/**
  * Created by aworton on 23/02/17.
  */
class ConcreteVirtualMachine extends VirtualMachine{

  private var stack: List[Int] = Nil

  override def execute(bc: Vector[ByteCode]): VirtualMachine = {
    bc.foreach(_.execute(this))
    this
  }

  override def executeOne(bc: Vector[ByteCode]): (Vector[ByteCode], VirtualMachine) = bc.toList match{
    case instruction :: tail => (tail.toVector, execute(Vector(instruction)))
    case Nil => (Vector(), this)
  }

  override def push(value: Int): VirtualMachine = {
    stack = value :: stack
    this
  }

  override def pop(): (Int, VirtualMachine) = stack match {
    case Nil => throw new MachineUnderflowException("Cannot pop from an empty stack")
    case head :: tail => {
      stack = tail.asInstanceOf[List[Int]]
      (head, this)
    }
  }

  override def state: Vector[Int] = stack match{
    case Nil => Vector()
    case head :: tail => Vector(head)
  }
}
