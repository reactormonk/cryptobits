import com.typesafe.sbt.pgp.PgpKeys

version := "1.1"
organization := "org.reactormonk"
name := "cryptobits"
scalaVersion := "2.11.12"

crossScalaVersions := Seq("2.10.7", "2.11.12", "2.12.8", "2.13.0-M5")

libraryDependencies ++= Seq(
  "org.scalacheck" %% "scalacheck" % "1.14.0" % Test,
  "commons-codec" % "commons-codec" % "1.10"
)

licenses := Seq(
  "MIT" -> url("https://opensource.org/licenses/MIT")
)
scmInfo := Some(ScmInfo(
  url("https://github.com/reactormonk/crypto-bits.git"),
  "scm:git:github.com/reactormonk/crypto-bits.git",
  Some("scm:git:git@github.com:reactormonk/crypto-bits.git")
))
homepage := Some(url("https://github.com/reactormonk/crypto-bits"))
developers := List(Developer(
  "reactormonk",
  "Simon Hafner",
  "",
  url("https://github.com/reactormonk")
))
publishMavenStyle := true
pomIncludeRepository := { _ => false }
publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}
credentials ++= {
  Seq("SONATYPE_USER", "SONATYPE_PASS").map(sys.env.get) match {
    case Seq(Some(user), Some(pass)) =>
      Seq(Credentials("Sonatype Nexus Repository Manager", "oss.sonatype.org", user, pass))
    case _ =>
      Seq.empty
  }
}

sbtrelease.ReleasePlugin.autoImport.releaseVersionBump := sbtrelease.Version.Bump.Bugfix
sbtrelease.ReleasePlugin.autoImport.releasePublishArtifactsAction := PgpKeys.publishSigned.value
