import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.streaming.twitter.TwitterUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}
import twitter4j.auth.OAuthAuthorization
import twitter4j.conf.ConfigurationBuilder

object TwitterToKafkaStream extends Serializable {
  def main(args: Array[String]): Unit = {
    val appName = "TwitterDAtaAnalysis12202018"
    val consumerKey = "<ConsumerKey>"
    val consumerSecret = "<ConsumerSecret>"
    val accessToken = "<AccessToken>"
    val accessSecret = "<AccessSecret>"
    val conf = new SparkConf()
    conf.setAppName(appName).setMaster("local[4]")
    val ssc = new StreamingContext(conf, Seconds(2))
    Logger.getRootLogger.setLevel(Level.ERROR)
    val configurationBuilder = new ConfigurationBuilder()
    configurationBuilder.setDebugEnabled(true)
      .setOAuthConsumerKey(consumerKey)
      .setOAuthConsumerSecret(consumerSecret)
      .setOAuthAccessToken(accessToken)
      .setOAuthAccessTokenSecret(accessSecret)
    val authenticate = new OAuthAuthorization(configurationBuilder.build())
    val stream = TwitterUtils.createStream(ssc, Some(authenticate)).filter(_.getLang() == "en").filter(_.getText.toString.contains("#"))
    stream.foreachRDD { rdd => {
      rdd.foreach { ele =>
        var hashTagEntityArray = ele.getHashtagEntities
        hashTagEntityArray.foreach { hashTag =>
          //if (isAboutApple(hashTag.getText)) {
          if (hashTag.getText.trim.matches("^[0-9a-zA-Z]+$")) {
            KafkaProducerRaw.sendRecordToKafka(ele.getCreatedAt.toString, hashTag.getText, ele.getText.replaceAll("\n", " "))
          }
        }
      }
    }
    }

    def isAboutApple(hashTag: String): Boolean = {
      var list = List[String]("apple", "iphone", "ipad", "applewatch", "ipod", "ios", "ilife")
      if (list.contains(hashTag.toLowerCase()))
        true
      else false
    }

    ssc.start()
    ssc.awaitTermination()
  }
}