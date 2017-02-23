package vm

import bc.ByteCode

/**
  * Represents an exception that can be thrown if a virtual machine
  * encounters an *underflow*. Because the virtual machine that we
  * are implementing is stack-based, an invalid program could try
  * to `pop` two operands from its stack of only one operand.
  *
  * You should use this in your implementation of your virtual machine!
  *
  * @param msg an exception message
  */
class MachineUnderflowException(msg: String) extends Exception(msg)

/**
  * Represents a stack-based virtual machine.
  *
  * An implementation of a virtual machine is capable of executing
  * a vector of [[bc.ByteCode]] values. This is where the command
  * pattern comes into play! That is, we defer the execution of
  * each bytecode until it is executed by the virtual machine.
  *
  * You will need to implement one of these!
  */
trait VirtualMachine {
  def execute(bc: Vector[ByteCode]): VirtualMachine

  def executeOne(bc: Vector[ByteCode]): (Vector[ByteCode], VirtualMachine)

  def push(value: Int): VirtualMachine

  def pop(): (Int, VirtualMachine)

  def state: Vector[Int]
}
