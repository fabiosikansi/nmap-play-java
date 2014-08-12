import play.Project._

name := """nmap-play-java"""

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  "org.webjars" %% "webjars-play" % "2.2.2", 
  "org.webjars" % "bootstrap" % "3.2.0",
  "org.webjars" % "d3js" % "3.4.11")

playJavaSettings
