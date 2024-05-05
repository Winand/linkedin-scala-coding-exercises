import org.scalatest.wordspec.AnyWordSpec
import PasswordChecker.*
import org.scalatest.matchers.should.Matchers.shouldBe

class PasswordCheckerTest extends AnyWordSpec {
  "isValidPassword" should {
    "detect invalid passwords" in {
      isValidPassword("z1x:bN") shouldBe false
      isValidPassword("qWeRtYul") shouldBe false
      isValidPassword("quizzle123") shouldBe false
    }
  }
  "isValidPassword" should {
    "detect invalid passwords w/o lowercase letters" in {
      isValidPassword("QU1ZL_TS") shouldBe false
    }
  }
  "isValidPassword" should {
    "detect valid passwords" in {
      isValidPassword("(ScalaChallenge2)") shouldBe true
      isValidPassword("b3stpAssw0rd!") shouldBe true
      isValidPassword("Qu1zl_ts") shouldBe true
    }
  }
}
