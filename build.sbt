import Dependencies._

ThisBuild / scalaVersion     := "2.12.8"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

val diodeVersion = "1.1.4"

lazy val root = (project in file("."))
  .settings(
    name := "hello-scala-js",

    // This is an application with a main method
    scalaJSUseMainModuleInitializer := true,
    emitSourceMaps := true,

    jsEnv := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv(),
    
    skip in packageJSDependencies := false,

    resolvers += Resolver.sonatypeRepo("snapshots"),

    libraryDependencies ++= Seq(
      scalaTest % Test,
      "org.scala-js" %%% "scalajs-dom" % "0.9.6",
      "com.github.ahnfelt" %%% "react4s" % "0.9.24-SNAPSHOT",
      "io.suzaku" %%% "diode" % diodeVersion
    ),

    Compile / npmDependencies ++= Seq(
      "react" -> "16.5.1",
      "react-dom" -> "16.5.1"),

    workbenchDefaultRootObject := Some(("target/scala-2.12/classes/index.html", "target/scala-2.12/"))
  )
  .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin, WorkbenchPlugin)


lazy val `todo-api` = (project in file("./todo-api"))
  .settings(
    name := "todo-api",

    libraryDependencies ++= Seq(
      scalaTest % Test,
      "com.typesafe.akka" %% "akka-actor" % "2.5.21",
      "com.typesafe.akka" %% "akka-http-spray-json" % "10.1.7",
      "com.typesafe.akka" %% "akka-http"   % "10.1.7",
      "com.typesafe.akka" %% "akka-stream" % "2.5.21"
    )
  )


