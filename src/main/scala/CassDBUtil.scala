import com.datastax.driver.core.TableMetadata
import org.apache.spark.streaming.{Seconds, StreamingContext}
import com.datastax.spark.connector._
import com.datastax.spark.connector.streaming._
import org.apache.spark._
import org.apache.spark.sql.SparkSession

object CassDBUtil {
  def main(args: Array[String]) {

    val conf = new SparkConf(true)
      .setMaster("local[2]")
      .setAppName("DatastaxTests")
      .set("spark.executor.memory", "1g")
      .set("spark.cassandra.connection.host", "localhost")
    //val conf = new SparkConf().setMaster("local[2]").
    // setAppName("scala_streaming_test").
    // set("spark.cassandra.connection.host", "localhost")
    val ssc = new SparkContext(conf);
    //val ssc = new StreamingContext(conf, Seconds(10))
    val con = ssc.cassandraTable("sentimentdb", "sentiment")
    val rdd = con.select("createdat", "hashtag", "sentiment", "tweet")
    println("Records : "+rdd.count())
    //    val sparkrdd = rdd.toJavaRDD()
    //  sparkrdd.collect()

  }
}
