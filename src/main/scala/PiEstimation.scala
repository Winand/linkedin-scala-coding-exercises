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

  def main(args: Array[String]): Unit = {
    val count = 100000
    val result = estimatePointsInQuarterCircle(count)
    println(result.toDouble / count * 4)
  }
}
