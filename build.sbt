name := "TDSA24_21111_30"

version := "0.1"

scalaVersion := "2.11.12"

libraryDependencies += "org.apache.spark" %% "spark-core" % "2.3.1"
libraryDependencies += "org.apache.spark" %% "spark-streaming" % "2.3.1" % "provided"
libraryDependencies += "org.apache.spark" %% "spark-streaming-twitter" % "1.6.3"
libraryDependencies += "org.twitter4j" % "twitter4j-core" % "4.0.7"
libraryDependencies += "edu.stanford.nlp" % "stanford-corenlp" % "3.9.2"
libraryDependencies += "edu.stanford.nlp" % "stanford-corenlp" % "3.9.2" classifier "models"
libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.1.1"
libraryDependencies += "com.datastax.spark" %% "spark-cassandra-connector" % "2.3.1"
