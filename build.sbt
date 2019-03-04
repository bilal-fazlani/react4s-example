import Dependencies._
import sbtcrossproject.CrossPlugin.autoImport.{CrossType, crossProject}

ThisBuild / scalaVersion := "2.12.8"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.example"
ThisBuild / organizationName := "example"
name := "scalajs-examples"

val diodeVersion = "1.1.4"

lazy val `react4s-app` = (project in file("./react4s-app"))
  .settings(
    name := "react4s-app",

    // This is an application with a main method
    scalaJSUseMainModuleInitializer := true,
    emitSourceMaps := true,

    jsEnv := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv(),

    skip in packageJSDependencies := false,

    resolvers += Resolver.bintrayRepo("hmil", "maven"),
    resolvers += Resolver.sonatypeRepo("snapshots"),

    libraryDependencies ++= Seq(
      scalaTest % Test,
      "org.scala-js" %%% "scalajs-dom" % "0.9.6",
      "com.github.ahnfelt" %%% "react4s" % "0.9.24-SNAPSHOT",
      "io.suzaku" %%% "diode" % diodeVersion
    ),

    Compile / npmDependencies ++= Seq(
      "react" -> "16.5.1",
      "react-dom" -> "16.5.1",
    ),

    scalacOptions += "-P:scalajs:sjsDefinedByDefault",
    webpackDevServerExtraArgs in fastOptJS := Seq("--inline", "--hot"),
    webpackResources := webpackResources.value +++ PathFinder(Seq(baseDirectory.value / "index.html")) ** "*.*",
    webpackDevServerExtraArgs in fastOptJS ++= Seq(
      "--content-base",
      baseDirectory.value.getAbsolutePath
    )
  )
  .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)
  .dependsOn(`todo-client`, `todo-api`.js)

lazy val `todo-server` = (project in file("./todo-server"))
  .settings(
    name := "todo-server",

    libraryDependencies ++= Seq(
      scalaTest % Test,
      "com.typesafe.akka" %% "akka-actor" % "2.5.21",
      "com.typesafe.akka" %% "akka-http-spray-json" % "10.1.7",
      "com.typesafe.akka" %% "akka-http" % "10.1.7",
      "com.typesafe.akka" %% "akka-stream" % "2.5.21",
      "ch.megard" %% "akka-http-cors" % "0.3.4"
    )
  ).dependsOn(`todo-api`.jvm)

lazy val `todo-client` = (project in file("./todo-client"))
  .settings(
    name := "todo-client",

    libraryDependencies ++= Seq(
      "fr.hmil" %%% "roshttp" % "2.2.3",
      "com.lihaoyi" %%% "upickle" % "0.7.1"
    )
  ).dependsOn(`todo-api`.js)
  .enablePlugins(ScalaJSPlugin)

lazy val `todo-api` =
  crossProject(JSPlatform, JVMPlatform)
    .crossType(CrossType.Pure)
    .in(file("./todo-api"))
    .settings(
      name := "todo-api"
    )

lazy val `todo-apiJVM` = `todo-api`.jvm
lazy val `todo-apiJS` = `todo-api`.js