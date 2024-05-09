import scala.annotation.tailrec
import scala.util.Random

object ReservoirSampling {
  @tailrec
  def sample[A](elts: List[A], iter: Int, curElem: Option[A]): Option[A] = {
    if (elts.isEmpty) curElem
    else {
      val cur = if (iter == 0 || Random.nextInt(iter) == 0)
        elts.headOption
      else curElem
      sample(elts.tail, iter + 1, cur)
    }
  }

  def sample[A](elts: List[A]): Option[A] =
    sample(elts, 0, None)

  def main(args: Array[String]): Unit = {
    println(sample(List(1, 2, 3, 4, 5, 6, 7, 8, 9)))
  }
}
