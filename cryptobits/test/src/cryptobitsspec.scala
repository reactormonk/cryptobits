package org.reactormonk

import org.scalacheck.Properties
import org.scalacheck.Prop.forAll
import java.time._

object CryptoSpecification extends Properties("Crypto") {
  val clock = Clock.systemUTC()
  val key = PrivateKey(scala.io.Codec.toUTF8("OhPh0AengeeshochooYu"))
  val key2 = PrivateKey(scala.io.Codec.toUTF8("OhPh0AengeeshochooYu2"))
  property("sign & validate") = forAll { message: String =>
    val crypto = CryptoBits(key)
    val signed = crypto.signToken(message, clock.millis.toString)
    crypto.validateSignedToken(signed).isDefined
  }
  property("reject invalid tokens") = forAll { message: String =>
    val crypto = CryptoBits(key)
    val crypto2 = CryptoBits(key2)
    val signed = crypto.signToken(message, clock.millis.toString)
    crypto2.validateSignedToken(signed).isEmpty
  }
}
