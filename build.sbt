lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "me.gumruns",
      scalaVersion := "2.12.4"
    )),
    name := "gumsearch"
  )

resolvers +=
  "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test
libraryDependencies += "org.seleniumhq.selenium" % "selenium-java" % "3.10.0" % Test
libraryDependencies +=  "org.scalaj" %% "scalaj-http" % "2.3.0"
libraryDependencies += "io.cucumber" %% "cucumber-scala" % "2.0.1"
libraryDependencies += "io.cucumber" % "gherkin" % "5.0.0"
libraryDependencies += "io.cucumber" % "cucumber-junit" % "2.4.0" % Test
libraryDependencies += "junit" % "junit" % "4.12" % Test

