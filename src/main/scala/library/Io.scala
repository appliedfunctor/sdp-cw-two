package library

/**
  * Created by aworton on 02/03/17.
  */
object Io {

  var lastOutput: Any = ""

  /**
    * Intermediary printing class to allow for better unit testing of printed values.
    * Hold the last printed value for checking.
    * @param message the supplied message to print
    */
  def output(message: Any): Unit = {
    println(message)
    lastOutput = message
  }
}
