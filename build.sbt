import Dependencies._

ThisBuild / scalaVersion     := "2.12.8"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .settings(
    name := "hello-scala-js",

    // This is an application with a main method
    scalaJSUseMainModuleInitializer := true,

    jsEnv := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv(),
    
    skip in packageJSDependencies := false,

    resolvers += Resolver.sonatypeRepo("snapshots"),

    libraryDependencies ++= Seq(
      scalaTest % Test,
      "org.scala-js" %%% "scalajs-dom" % "0.9.6",
      "com.github.ahnfelt" %%% "react4s" % "0.9.24-SNAPSHOT"
//      "org.querki" %%% "jquery-facade" % "1.2"
    ),

//    jsDependencies +=
//      "org.webjars" % "jquery" % "2.2.1" / "jquery.js" minified "jquery.min.js"
  )
  .enablePlugins(ScalaJSPlugin)



