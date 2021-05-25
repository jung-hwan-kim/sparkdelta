## Journey into Delta Lake

```shell
bin/spark-shell 
--master spark://KAN6584:7077  --packages io.delta:delta-core_2.12:0.8.0 --conf "spark.sql.extensions=io.delta.sql.DeltaSparkSessionExtension" --conf "spark.sql.catalog.spark_catalog=org.apache.spark.sql.delta.catalog.DeltaCatalog"
```



```shell
spark-submit --class "SimpleApp" --master local ~/git/sparkdelta/target/scala-2.12/sparkdelta-assembly-0.1.jar
```