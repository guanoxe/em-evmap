import org.scalajs.sbtplugin.cross.CrossProject

scalaVersion in ThisBuild := "2.12.2"

lazy val em_evmap = CrossProject("em-evmap-jvm", "em-evmap-js", file("."), CrossType.Full)
  .settings(
    organization := "com.koroc",
    name := "em-evmap",
    version := "0.6.5",
    libraryDependencies ++= Seq(
      "org.slf4j" % "slf4j-api" % "1.7.22",
      "org.scalatest" %% "scalatest" % "3.0.1" % "test",
      "com.typesafe.akka" %% "akka-http-testkit" % "10.0.10" % "test"
    ),
    resolvers += "koroc repo" at "http://koroc.nl/repository/maven-releases"
  )
  .jvmSettings(
    publishArtifact in (Compile, packageDoc) := false,
    mainClass in assembly := Some("com.koroc.evmap.Main"),
    test in assembly := {},
    libraryDependencies ++= Seq(
      "ch.qos.logback" % "logback-classic" % "1.1.9",
      "com.typesafe.akka" %% "akka-slf4j" % "2.4.19",
      "com.typesafe.akka" %% "akka-http" % "10.0.10",
      "org.scala-lang.modules" %% "scala-xml" % "1.0.6",
      "com.koroc" %% "em-backend" % "0.8.1",
      "com.koroc" %% "em-io" % "0.1.12",
      "com.koroc" %% "em-jsonld" % "0.1.13"
    )
  )
  .jsSettings(
    publishArtifact in (Compile, packageDoc) := false,
    artifactPath in (Compile, fastOptJS) := file("/home/sander/projects/Em/em-evmap/web/js/evmap.js"),
    artifactPath in (Compile, fullOptJS) := file("/home/sander/projects/Em/em-evmap/web/js/evmap.js"),
    libraryDependencies ++= Seq(
      "com.koroc" %%% "em-sjs" % "1.5.3",
      "com.koroc" %%% "em-jsonld" % "0.1.13"
    )
  )
lazy val em_evmap_jvm = em_evmap.jvm
lazy val em_evmap_js = em_evmap.js

