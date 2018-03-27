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

libraryDependencies ++= Seq("org.scalatest" %% "scalatest" % "3.0.5", "org.scalatest" %% "scalatest" % "3.0.4" % Test)
libraryDependencies += "org.slf4j" % "slf4j-api" % "1.7.25"
libraryDependencies += "org.seleniumhq.selenium" % "selenium-java" % "3.10.0"
libraryDependencies += "org.seleniumhq.selenium" % "selenium-firefox-driver" % "3.10.0"
libraryDependencies +=  "org.scalaj" %% "scalaj-http" % "2.3.0"
libraryDependencies += "io.cucumber" %% "cucumber-scala" % "2.0.1"
libraryDependencies += "io.cucumber" % "gherkin" % "5.0.0"
libraryDependencies += "io.cucumber" % "cucumber-junit" % "2.4.0" % Test
libraryDependencies += "junit" % "junit" % "4.12" % Test
