ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.12"
enablePlugins(JmhPlugin)
lazy val root = (project in file("."))
  .settings(
    name := "TestowanieSbt",
    libraryDependencies ++= Seq(
      "org.openjdk.jmh" % "jmh-core" % "1.37" % Test,
      "org.openjdk.jmh" % "jmh-generator-bytecode" % "1.37" % Test,
      "org.openjdk.jmh" % "jmh-generator-reflection" % "1.37" % Test,
      "org.openjdk.jmh" % "jmh-generator-asm" % "1.37" % Test
    )
  )
