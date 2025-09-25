lazy val root = (project in file("."))
  .settings(
    name := "myapp",
    version := "0.1.0",
    scalaVersion := "2.13.12",

    libraryDependencies ++= Seq(
      // MongoDB reactive driver
      "org.mongodb.scala" %% "mongo-scala-driver" % "4.11.0",

      // MacWire DI
      "com.softwaremill.macwire" %% "macros" % "2.5.8" % Provided,
      "com.softwaremill.macwire" %% "util"   % "2.5.8",
      "com.softwaremill.macwire" %% "proxy"  % "2.5.8",

      // Akka HTTP & Streams
      "com.typesafe.akka" %% "akka-actor-typed" % "2.8.5",
      "com.typesafe.akka" %% "akka-stream"      % "2.8.5",
      "com.typesafe.akka" %% "akka-http"        % "10.5.2",
      "com.typesafe.akka" %% "akka-http-spray-json" % "10.5.2",

      // Spray JSON
      "io.spray"          %% "spray-json"       % "1.3.6"
    )
  )
