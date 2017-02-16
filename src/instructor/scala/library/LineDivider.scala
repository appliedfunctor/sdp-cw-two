package library

/**
  * Created by mmkeri on 16/02/2017.
  */
object LineDivider {
  def lineDivider(line: String): Tuple2[String, String] = {

    val dividedLine = line.split(" ")
    if(dividedLine.length == 1){
      Tuple2(dividedLine(0), " ")
    } else if (dividedLine.length == 2){
      Tuple2(dividedLine(0), dividedLine(1))
    } else {
      throw new IllegalArgumentException
    }
  }
}
