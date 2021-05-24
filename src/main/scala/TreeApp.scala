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
    var df = spark.read.format("csv").option("header", true).schema(treeSchema).load("/var/data/csv/trees.csv")
    // df.write.format("delta").saveAsTable("trees") // when we want to store it to hive metastore
    df.dtypes
    df.show()

    var trees = spark.read.format("delta").load("/var/data/delta/trees")

    trees.createTempView("trees")
    spark.sql("describe table trees").show()
    spark.sql("select count(*), avg(girth) from trees").show()
    trees.dtypes
    spark.stop()
  }
}
