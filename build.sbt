name := "candylib"

version := "1.5.5"

scalaVersion := "2.12.1"

//libraryDependencies := libraryDependencies.value ++ Seq(
//  "org.scala-lang.modules" %% "scala-xml" % "1.0.6",
//  "org.scalafx" %% "scalafx" % "8.0.102-R11",
//  "org.scalafx" %% "scalafxml-core-sfx8" % "0.3",
//  "com.typesafe.akka" %% "akka-actor" % "2.4.16"
//)

libraryDependencies += "org.scalatest" % "scalatest_2.12" % "3.0.1" % "test"
// https://mvnrepository.com/artifact/com.github.albfernandez/juniversalchardet
libraryDependencies += "com.github.albfernandez" % "juniversalchardet" % "2.0.0"



assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)

//assemblyJarName in assembly := s"${name.value}_${scalaVersion.value}-${version.value}.jar"
