import com.alejandrohdezma.sbt.github.SbtGithubPlugin
import sbt.Keys._
import sbt._

object ProjectPlugin extends AutoPlugin {

  override def trigger: PluginTrigger = allRequirements

  override def requires: Plugins = SbtGithubPlugin

  object autoImport {

    lazy val testkitSettings = Seq(
      libraryDependencies ++= Seq(
        "org.scalameta" %% "munit"                           % "0.7.7",
        "com.whisk"     %% "docker-testkit-core"             % "0.9.9",
        "com.whisk"     %% "docker-testkit-impl-spotify"     % "0.9.9"   % Test,
        "com.whisk"     %% "docker-testkit-impl-docker-java" % "0.9.9"   % Test,
        "com.whisk"     %% "docker-testkit-samples"          % "0.9.9"   % Test,
        "ch.qos.logback" % "logback-classic"                 % "1.2.3"   % Test,
        "org.postgresql" % "postgresql"                      % "42.2.12" % Test
      )
    )
  }

  override def projectSettings: Seq[Def.Setting[_]] =
    Seq(
      testFrameworks += new TestFramework("munit.Framework"),
      fork in Test := true
    )
}
