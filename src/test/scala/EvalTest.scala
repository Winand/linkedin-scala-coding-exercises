import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class EvalTest extends AnyWordSpec with Matchers {
  "literals" should {
    "evaluate to themselves" in {
      Eval(Expression(1.0)) shouldBe 1.0
      Eval(Expression(3.0)) shouldBe 3.0
      Eval(Expression(5.0)) shouldBe 5.0
      Eval(Expression(7.0)) shouldBe 7.0
    }
  }

  "complex expressions" should {
    "evaluate correctly" in {
      Eval(Expression(1.0) + Expression(3.0)) shouldBe 4.0
      Eval(Expression(1.0) * Expression(3.0)) shouldBe 3.0
      Eval(Expression(4.0) / Expression(2.0) + Expression(2.0)) shouldBe 4.0
      Eval(Expression(1.0) * Expression(3.0) - Expression(2.0)) shouldBe 1.0
    }
  }
}
