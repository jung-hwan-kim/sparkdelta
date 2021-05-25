import java.io.File
import java.util.UUID
import collection.JavaConverters._
import collection.mutable._

object Main {

  def main(args: Array[String]) {

    val fileList = new File("/var/data").listFiles(!_.isHidden)
    println(fileList)

    val names = fileList.map(f => ">" + f.getName)
    for (name <- names) println(name)
  }
}
