import java.io.{BufferedWriter, File, FileWriter}
import java.util.{Date, Properties}
import org.apache.kafka.clients.producer._

object KafkaProducerRaw {
  val props = new Properties()
  props.put("bootstrap.servers", "localhost:9092")
  props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  val TOPIC = "hashtagtweets_topic_new"
  def sendRecordToKafka(createdAt: String, hashTag:String, message: String): Unit = {
    val file = new File("hello.txt")
    val bw = new BufferedWriter(new FileWriter(file))
    bw.write(createdAt+" "+hashTag+" "+message)
    val producer = new KafkaProducer[String, String](props)
    val record = new ProducerRecord(TOPIC, createdAt, hashTag+"\t"+message)
    producer.send(record)
    producer.close()
    bw.close()
  }
}