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

  final case class State[A](cur: Option[A], iter: Int)

  object State {
    def empty[A]: State[A] = State(None, 0)
  }

  @tailrec
  def sample[A](elts: List[A], state: State[A]): Option[A] = {
    if (elts.isEmpty) state.cur
    else {
      val cur = if (state.iter == 0 || Random.nextInt(state.iter) == 0)
        elts.headOption
      else state.cur
      sample(elts.tail, State(cur, state.iter + 1))
    }
  }

  def sampleV2[A](elts: List[A]): Option[A] =
    sample(elts, State.empty)

  def sampleV3[A](elts: List[A]): Option[A] =
    //    sample(elts, State.empty)
    elts.foldLeft(State.empty[A])((state, el) =>
      val State(_, iter) = state
      val cur = if (iter == 0 || Random.nextInt(iter) == 0) Some(el) else state.cur
      State(cur, iter + 1)
    ).cur

  def sample[A](elts: List[A]): Option[A] =
    elts.foldLeft(State.empty[A])((state, el) =>
      val State(_, iter) = state
      val prob = 1.0 / (iter + 1)
      val cur = if (Random.nextDouble() < prob) Some(el) else state.cur
      State(cur, iter + 1)
    ).cur

  def main(args: Array[String]): Unit = {
    println(sample(List(1, 2, 3, 4, 5, 6, 7, 8, 9)))
  }
}
