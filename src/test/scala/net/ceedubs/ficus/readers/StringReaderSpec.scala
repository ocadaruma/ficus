package net.ceedubs.ficus
package readers

import com.typesafe.config.ConfigFactory
import org.scalacheck.Prop
import ConfigSerializerOps._

class StringReaderSpec extends Spec with StringReader { def is =
  "The String value reader should" ^
    "read a String" ! readString

  def readString = Prop.forAll(jsonStringValue) { string: String =>
    val cfg = ConfigFactory.parseString(s"myValue = ${string.asConfigValue}")
    stringValueReader.read(cfg, "myValue") must beEqualTo(string)
  }
}
