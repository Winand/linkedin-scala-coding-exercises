import scala.annotation.tailrec
import scala.util.Random.nextDouble
import scala.math.sqrt

object PiEstimation {
  /*
  https://blogs.sas.com/content/iml/2016/03/14/monte-carlo-estimates-of-pi.html
  https://www.mathsisfun.com/algebra/circle-equations.html
  x^2 + y^2 = 1
  y = sqrt(1 - x^2)
   */
  def checkNewPoint: Boolean = {
    val x = nextDouble()
    val y = nextDouble()
    // sqrt(x * x + y * y) <= 1
    val yForX = sqrt(1 - x * x)
    y < yForX
  }

  @tailrec
  def estimatePointsInQuarterCircle(count: Int, inside: Int = 0): Int =
    if (count == 0) inside
    else estimatePointsInQuarterCircle(
      count - 1,
      if (checkNewPoint) inside + 1 else inside
    )

  /**
   * Solution from course author.
   */
  def pi(iterations: Long): Double = {
    def loop(inside: Long, total: Long): Double =
      if total == iterations then (4.0 * inside.toDouble / total.toDouble)
      else {
        val x = nextDouble - 0.5
        val y = nextDouble - 0.5

        val dist = math.sqrt((x * x) + (y * y))

        if dist < 0.5 then loop(inside + 1, total + 1)
        else loop(inside, total + 1)
      }

    loop(0, 0)
  }

  def main(args: Array[String]): Unit = {
    val count = 1000000
    for _ <- 1 to 10 yield {
      val result = estimatePointsInQuarterCircle(count)
      println(s"Solution: ${result.toDouble / count * 4}")
      println(s"Example solution: ${pi(count)}")
    }
  }
}
