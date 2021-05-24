import org.apache.spark.sql.SparkSession

object SimpleApp {

  // an example from Apache Spark documentation
  def main(args: Array[String]) {
    val logFile = "README.md"
    val spark = SparkSession.builder.appName("Simple App").getOrCreate()
    val logData = spark.read.textFile(logFile).cache()
    val numAs = logData.filter(line => line.contains("a")).count()
    val numBs = logData.filter(line => line.contains("b")).count()

    println(s"Lines with a: $numAs, Lines with b: $numBs")
    spark.stop()
  }
}