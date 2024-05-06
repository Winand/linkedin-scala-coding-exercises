import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class RegExMatchesTest extends AnyWordSpec with Matchers {
  "snakeToCamel" should {
    "convert snake case to camel case correctly" in {
      RegExMatches.snakeToCamel("print_all") shouldBe "printAll"
      RegExMatches.snakeToCamel("snake_to_camel") shouldBe "snakeToCamel"
      RegExMatches.snakeToCamel("get_hello_world") shouldBe "getHelloWorld"
    }
  }
}
