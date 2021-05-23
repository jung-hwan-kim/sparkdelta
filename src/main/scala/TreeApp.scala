import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.{DoubleType, StringType, StructField, StructType, TimestampType}

object TreeApp {
  val treeSchema = StructType(Array(
    StructField("id", StringType, true),
    StructField("girth", DoubleType, true),
    StructField("height", DoubleType, true),
    StructField("volume", DoubleType, true)))

  def main(args: Array[String]) {
    val spark = SparkSession.builder.appName("Simple Application").getOrCreate()
    val logData = spark.read.textFile(logFile).cache()
    val numAs = logData.filter(line => line.contains("a")).count()
    val numBs = logData.filter(line => line.contains("b")).count()
    println(s"Lines with a: $numAs, Lines with b: $numBs")
    spark.stop()
  }
}
