lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "me.gumruns",
      scalaVersion := "2.12.4"
    )),
    name := "gemsearch"
  )

resolvers +=
  "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test
libraryDependencies += "org.seleniumhq.selenium" % "selenium-java" % "3.10.0" % Test
libraryDependencies +=  "org.scalaj" %% "scalaj-http" % "2.3.0"
libraryDependencies +=  "ru.yandex.qatools.allure" % "allure-scalatest_2.10" % "1.4.0-SNAPSHOT" % Test

testOptions in Test ++= Seq(
  Tests.Argument(TestFrameworks.ScalaTest, "-oD"),
  Tests.Argument(TestFrameworks.ScalaTest, "-C", "ru.yandex.qatools.allure.scalatest.AllureReporter")
)
