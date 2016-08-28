# crypto-bits
Simple crypto for signing auth tokens.

# Sample usage

```scala
val key = PrivateKey(scala.io.Codec.toUTF8(string))
val crypto = CryptoBits(key)
val clock = Clock.systemUTC()
// or anything else for the nonce, should be something different each time.
val signed = crypto.signToken(message, clock.millis.toString)
val data = crypto.validateSignedToken(signed)
```
