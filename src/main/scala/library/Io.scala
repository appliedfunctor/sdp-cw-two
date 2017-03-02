package library

/**
  * Created by aworton on 02/03/17.
  */
object Io {

  var lastOutput: Any = ""

  def output(message: Any): Unit = {
    println(message)
    lastOutput = message
  }
}
