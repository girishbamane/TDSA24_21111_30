import java.util
import java.util.Properties
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.spark.{SparkConf, SparkContext}
import scala.collection.JavaConverters._

object SentimentAnalysis {
  def main(args: Array[String]): Unit = {
    val TOPIC = "hashtagtweets_topic_new"
    val props = new Properties()
    props.put("bootstrap.servers", "localhost:9092")
    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("group.id", "test-consumer-group")
    val conf = new SparkConf(true)
      .setMaster("local[2]")
      .setAppName("DatastaxTests")
      .set("spark.executor.memory", "1g")
      .set("spark.cassandra.connection.host", "localhost")
    val ssc = new SparkContext(conf);
    val consumer = new KafkaConsumer[String, String](props)
    consumer.subscribe(util.Collections.singletonList(TOPIC))
    while (true) {
      val records = consumer.poll(2)
      for (record <- records.asScala) {
        var tweetData = record.value()
        var hashTag=""
        var tweet = tweetData.substring(tweetData.indexOf("\t")).replaceAll("[\\.$|,|;|']", "").trim
        if(tweetData.contains("\t")){
          hashTag = tweetData.substring(0,tweetData.indexOf("\t"))
          CassDBUtil.saveDataToCassandra(ssc,record.key(),hashTag,SentimentAnalysisUtils.detectSentiment(tweet).toString,tweet)
        }
      }
    }
  }
}