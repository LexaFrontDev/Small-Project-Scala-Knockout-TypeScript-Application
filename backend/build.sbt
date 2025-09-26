import sbtassembly.AssemblyPlugin.autoImport._

lazy val akkaVersion         = "2.8.5"
lazy val akkaHttpVersion     = "10.5.2"
lazy val akkaHttpCorsVersion = "1.1.3"
lazy val macwireVersion      = "2.5.8"
lazy val logbackVersion      = "1.4.11"
lazy val mongoScalaVersion   = "4.11.0"
lazy val sprayJsonVersion    = "1.3.6"

lazy val root = (project in file("."))
  .enablePlugins(sbtassembly.AssemblyPlugin)
  .settings(
    name := "myapp",
    version := "0.1.0",
    scalaVersion := "2.13.12",

    libraryDependencies ++= Seq(
      // MongoDB reactive driver
      "org.mongodb.scala" %% "mongo-scala-driver" % mongoScalaVersion,

      // MacWire DI
      "com.softwaremill.macwire" %% "macros" % macwireVersion % Provided,
      "com.softwaremill.macwire" %% "util"   % macwireVersion,
      "com.softwaremill.macwire" %% "proxy"  % macwireVersion,

      // Akka HTTP & Streams
      "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
      "com.typesafe.akka" %% "akka-stream"      % akkaVersion,
      "com.typesafe.akka" %% "akka-http"        % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
      "ch.qos.logback" % "logback-classic" % logbackVersion,
      "ch.megard" %% "akka-http-cors" % akkaHttpCorsVersion,

      // Spray JSON
      "io.spray" %% "spray-json" % sprayJsonVersion
    ),

    assembly / assemblyMergeStrategy := {
      case PathList("META-INF", "MANIFEST.MF") => MergeStrategy.discard
      case PathList("META-INF", "native-image", _*) => MergeStrategy.discard
      case PathList("META-INF", xs @ _*) => MergeStrategy.first
      case "module-info.class" => MergeStrategy.discard
      case "reference.conf" => MergeStrategy.concat
      case "application.conf" => MergeStrategy.concat
      case _ => MergeStrategy.first
    }
  )

lazy val migrations = (project in file("migration"))
  .enablePlugins(sbtassembly.AssemblyPlugin)
  .dependsOn(root)
  .settings(
    name := "migrations",
    scalaVersion := "2.13.12",
    libraryDependencies ++= Seq(
      "org.mongodb.scala" %% "mongo-scala-driver" % mongoScalaVersion
    ),
    assembly / assemblyMergeStrategy := {
      case PathList("META-INF", "MANIFEST.MF") => MergeStrategy.discard
      case PathList("META-INF", "native-image", _*) => MergeStrategy.discard
      case PathList("META-INF", xs @ _*) => MergeStrategy.first
      case "module-info.class" => MergeStrategy.discard
      case "reference.conf" => MergeStrategy.concat
      case "application.conf" => MergeStrategy.concat
      case _ => MergeStrategy.first
    }
  )
