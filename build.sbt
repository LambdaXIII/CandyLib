name := "CandyLib"

version := "1.0"

scalaVersion := "2.12.1"

libraryDependencies := libraryDependencies.value ++ Seq(
  "org.scala-lang.modules" %% "scala-xml" % "1.0.6",
  "org.scalafx" %% "scalafx" % "8.0.102-R11",
  "org.scalafx" %% "scalafxml-core-sfx8" % "0.3",
  "com.typesafe.akka" %% "akka-actor" % "2.4.16"
)

libraryDependencies += "org.scalatest" % "scalatest_2.12" % "3.0.1" % "test"
