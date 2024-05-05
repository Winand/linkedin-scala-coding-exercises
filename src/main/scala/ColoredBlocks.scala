import cats.effect.unsafe.implicits.global
import doodle.image.Image
import doodle.core.Color
import doodle.image.syntax.all.*
import doodle.java2d.*
import scala.annotation.tailrec

object ColoredBlocks {
  // The block used to create the stack of blocks
  val block = Image.square(40).fillColor(Color.crimson)

  // Example showing how to create a stack of blocks
  val stack = block.above(block.above(Image.empty))

  def stack(count: Int): Image = {
    @tailrec
    def stackInternal(count: Int, b: Image): Image = {
      if (count == 0) b
      else stackInternal(count - 1, block.above(b))
    }
    stackInternal(count, Image.empty)
  }
  
  def stack_recursive(count: Int): Image = {
    if (count == 0) Image.empty
    else block.above(stack(count - 1))
  }

  // Call this to draw a stack of blocks
  @main def drawBlocks(): Unit =
    stack(5).draw()
}
