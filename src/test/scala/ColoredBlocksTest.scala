import org.scalatest.wordspec.AnyWordSpec
import ColoredBlocks.*
import doodle.image.Image
import org.scalatest.matchers.should.Matchers

class ColoredBlocksTest extends AnyWordSpec with Matchers {

  "stack" should {
    "generate correct number of stacked blocks" in {
      stack(3) shouldBe block.above(block.above(block.above(Image.empty)))
    }
  }
  "stack_recursive" should {
    "generate correct number of stacked blocks" in {
      stack_recursive(2) shouldBe block.above(block.above(Image.empty))
    }
  }
}
