name := """peeeqplay"""

version := "1.0-SNAPSHOT"

scalaVersion := "2.10.1"

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

resolvers += "Sonatype snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"

libraryDependencies ++= Seq(
  // Select Play modules
  filters,   // A set of built-in filters
  javaCore,  // The core Java API
  // WebJars pull in client-side web libraries
  "org.webjars" % "jquery" % "2.0.3-1",
  "org.webjars" %% "webjars-play" % "2.2.0",
  "org.webjars" % "bootstrap" % "2.3.1",
  "com.typesafe.slick" %% "slick" % "1.0.1",
  "com.typesafe.play" %% "play-slick" % "0.5.0.2-SNAPSHOT",
  "mysql" % "mysql-connector-java" % "5.1.21",
  "com.github.tototoshi" %% "slick-joda-mapper" % "0.4.0"
)

play.Project.playScalaSettings


