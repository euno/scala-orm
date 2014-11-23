import _root_.sbt.Keys._

name := "scala-orm"

version := "1.0"

scalaVersion := "2.11.4"

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % "2.1.0",
  "com.typesafe.slick" %% "slick-codegen" % "2.1.0",
  "org.scalikejdbc" %% "scalikejdbc" % "2.2.0",
  "mysql" % "mysql-connector-java" % "5.1.34"
)

scalikejdbcSettings
