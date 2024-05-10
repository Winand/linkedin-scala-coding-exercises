import Store.Fulfillment
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class StoreTest extends AnyWordSpec with Matchers {
  "Successful order" should {
    "be fulfilled as expected" in {
      val result = Store.processOrderForUser(1)
      result shouldBe Some(Fulfillment("Today"))
    }
  }

  "Unsuccessful orders" should {
    "fail" in {
      Store.processOrderForUser(0) shouldBe None
      Store.processOrderForUser(2) shouldBe None
      Store.processOrderForUser(3) shouldBe None
      Store.processOrderForUser(4) shouldBe None
    }
  }
}
