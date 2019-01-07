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
    val ssc = new SparkContext(conf);
    val con = ssc.cassandraTable("sentimentdb", "sentiment")
    val rdd = con.select("createdat", "hashtag", "sentiment", "tweet")
    println("Records : " + rdd.count())
    rdd.foreach(row => println("Row : " + " CreatedDate: " + row.getString(0) +
      " HashTag: " + row.getString(1) +
      " Sentiment: "+ row.getString(2)+
      " Tweet: "+ row.getString(3)
    ))

  }
}
