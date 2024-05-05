import org.scalatest.wordspec.AnyWordSpec
import PalindromeDetector.*
import org.scalatest.matchers.should.Matchers.shouldBe

class PalindromeDetectorTest extends AnyWordSpec {
  "Palindromes" should {
    "be correctly identified" in {
      isPalindrome("Akka") shouldBe true
      isPalindrome("radar") shouldBe true
      isPalindrome("level") shouldBe true
      isPalindrome("kayak") shouldBe true
    }
  }
  "Non-palindromes" should {
    "be correctly identified" in {
      isPalindrome("canoe") shouldBe false
      isPalindrome("bumpy") shouldBe false
      isPalindrome("lighter") shouldBe false
    }
  }
  "Case" should {
    "be ignored" in {
      isPalindrome("tacocat") shouldBe true
      isPalindrome("Tacocat") shouldBe true
      isPalindrome("TACOCAT") shouldBe true
    }
  }
}
