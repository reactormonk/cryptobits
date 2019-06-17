import mill._, scalalib._, publish._

object cryptobits extends mill.Cross[CryptobitsModule]("2.13.0", "2.12.8", "2.11.12", "2.10.7")

class CryptobitsModule(val crossScalaVersion: String) extends CrossScalaModule with PublishModule {

  def publishVersion = "1.2"

  def ivyDeps = Agg(ivy"commons-codec:commons-codec:1.12")

  object test extends Tests {
    def ivyDeps = Agg(ivy"org.scalacheck::scalacheck:1.14.0")

    def testFrameworks = Seq("org.scalacheck.ScalaCheckFramework")
  }

  def pomSettings = PomSettings(
    organization = "org.reactormonk",
    description = "Simple crypto for signing auth tokens",
    url = "https://github.com/reactormonk/cryptobits",
    licenses = Seq(License.MIT),
    versionControl = VersionControl.github("reactormonk", "cryptobits"),
    developers = Seq(
      Developer("reactormonk", "Simon Hafner", "https://github.com/reactormonk")
    )
  )
}
