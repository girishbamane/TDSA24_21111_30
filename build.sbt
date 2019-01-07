name := "TDSA24_21111_30"

version := "0.1"

scalaVersion := "2.11.12"

libraryDependencies += "org.apache.spark" %% "spark-core" % "2.4.0"
libraryDependencies += "org.apache.spark" %% "spark-streaming" % "2.4.0" % "provided"
libraryDependencies += "org.apache.spark" %% "spark-streaming-twitter" % "1.6.3"
libraryDependencies += "org.twitter4j" % "twitter4j-core" % "4.0.7"
libraryDependencies += "edu.stanford.nlp" % "stanford-corenlp" % "3.9.2"
libraryDependencies += "edu.stanford.nlp" % "stanford-corenlp" % "3.9.2" classifier "models"
libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.1.1"
/*libraryDependencies += "com.datastax.cassandra" % "cassandra-driver-extras" % "3.0.8"
libraryDependencies += "com.datastax.cassandra" % "cassandra-driver-core" % "3.0.8"
libraryDependencies += "com.datastax.cassandra" % "cassandra-driver-mapping" % "3.0.8"*/

// https://mvnrepository.com/artifact/com.datastax.spark/spark-cassandra-connector
libraryDependencies += "com.datastax.spark" %% "spark-cassandra-connector" % "2.4.0"
