package bc

/**
  * [[ByteCodeFactory]] defines a factory interface for creating
  * [[ByteCode]] objects. You will need to extend this to provide
  * your own implementation of a [[ByteCodeFactory]].
  */
trait ByteCodeFactory {
  def make(byte: Byte, args: Int*): ByteCode
}
