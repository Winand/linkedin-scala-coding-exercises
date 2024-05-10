import cats.effect.unsafe.implicits.global
import doodle.image.Image
import doodle.core.Color
import doodle.image.syntax.all.*
import doodle.java2d.*

object Sierpinski {
  val triangle = Image.equilateralTriangle(10).fillColor(Color.pink)

  def sierpinski(depth: Int, img: Image): Image =
    if depth == 1 then img
    else
      val newImg = img.beside(img).below(img)
      sierpinski(depth - 1, newImg)

  def sierpinski(depth: Int): Image =
    sierpinski(depth, triangle)

  /**
   * Recursive version.
   */
  def sierpinski_recursive(depth: Int): Image =
    if depth == 1 then triangle
    else
      val img = sierpinski_recursive(depth - 1)
      img.beside(img).below(img)

  @main def drawSierpinski(): Unit =
    sierpinski(5).draw()
}
