import org.apache.spark.sql.types.{StringType, StructField, StructType, TimestampType}
import org.apache.spark.sql.{DataFrame, Row, SparkSession}

import java.sql.Timestamp
import java.time.Instant
import java.util.UUID

object SimpleApp {

  def log(spark: SparkSession, cat: String, msg: String) {
    val logData = Seq (
      Row(UUID.randomUUID().toString, cat, msg, java.sql.Timestamp.from(java.time.Instant.now))
    )
    val logSchema = List(
      StructField("id", StringType, false),
      StructField("cat", StringType, false),
      StructField("msg", StringType, true),
      StructField("date", TimestampType, true)
    )

    val logDf = spark.createDataFrame(
      spark.sparkContext.parallelize(logData),
      StructType(logSchema)
    )
    logDf.write.format("delta").mode("append").save("/var/data/delta/log")
  }

  // an example from Apache Spark documentation
  def main(args: Array[String]) {
    val logFile = "README.md"
    val spark = SparkSession.builder.appName("Simple App").getOrCreate()

    log(spark, "ingest", "testing..")
    spark.stop()
  }
}