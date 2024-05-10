enum DecisionTree {
  def decide(value: Int): Boolean =
    this match
      case Result(result) => result
      case Split(split, lessThanOrEqual, greaterThan) =>
        if (value <= split)
          lessThanOrEqual.decide(value)
        else greaterThan.decide(value)

  case Result(result: Boolean)
  case Split(
              split: Int,
              lessThanOrEqual: DecisionTree,
              greaterThan: DecisionTree
            )
}
