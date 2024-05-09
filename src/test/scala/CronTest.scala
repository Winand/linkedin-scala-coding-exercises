import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class CronTest extends AnyWordSpec with Matchers {
  import Time.*
  
  "All stars" should {
    "be parsed correctly" in {
      val result = Cron.parse("* * * * * echo 'Hello'")
      result shouldBe CronLine(All, All, All, All, All, "echo 'Hello'")
    }
  }
  
  "All numbers" should {
    "be parsed correctly" in {
      val result = Cron.parse("15 2 3 4 2 echo 'Hello'")
      result shouldBe CronLine(At(15), At(2), At(3), At(4), At(2), "echo 'Hello'")
    }
  }
  
  "Mixture of number and star" should {
    "be parsed correctly" in {
      val result = Cron.parse("15 * 3 * * echo 'Hello'")
      result shouldBe CronLine(At(15), All, At(3), All, All, "echo 'Hello'")
    }
  }
}
