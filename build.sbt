lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "me.gumruns",
      scalaVersion := "2.12.4"
    )),
    name := "gemsearch"
  )

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test
libraryDependencies += "org.seleniumhq.selenium" % "selenium-java" % "3.10.0" % Test
libraryDependencies +=  "org.scalaj" %% "scalaj-http" % "2.3.0"