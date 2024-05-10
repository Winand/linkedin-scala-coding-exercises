
enum Expression {
  def +(that: Expression): Expression =
    Addition(this, that)

  def -(that: Expression): Expression =
    Subtraction(this, that)

  def *(that: Expression): Expression =
    Multiplication(this, that)

  def /(that: Expression): Expression =
    Division(this, that)

  case Literal(value: Double)
  case Addition(left: Expression, right: Expression)
  case Subtraction(left: Expression, right: Expression)
  case Multiplication(left: Expression, right: Expression)
  case Division(left: Expression, right: Expression)
}

object Expression {
  def apply(value: Double): Expression = Literal(value)
}

object Eval {

  import Expression.*

  def apply(expr: Expression): Double =
    expr match
      case Literal(value) => value
      case Addition(left, right) => Eval(left) + Eval(right)
      case Subtraction(left, right) => Eval(left) - Eval(right)
      case Multiplication(left, right) => Eval(left) * Eval(right)
      case Division(left, right) => Eval(left) / Eval(right)


  def main(args: Array[String]): Unit = {
    println(Eval(Expression(9) + Expression(1)))
  }
}
