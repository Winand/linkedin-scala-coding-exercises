import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ReservoirSamplingTest extends AnyWordSpec with Matchers {
  "sampling empty list" should {
    "return None" in {
      ReservoirSampling.sample(List.empty[Int]) shouldBe None
    }
  }

  "sampling one element list" should {
    "return single element" in {
      ReservoirSampling.sample(List(1)) shouldBe Some(1)
    }
  }

  "sampling larger list" should {
    "return an element within the list" in {
      val Some(result) = ReservoirSampling.sample(List(0, 1, 2, 3, 4)): @unchecked
      assert(result >= 0 && result <= 4, s"Result is $result")
    }
  }
}
