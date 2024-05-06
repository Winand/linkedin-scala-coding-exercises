object RegExMatches {
  def snakeToCamel(string: String): String =
    val pattern = "_[a-z]".r
    pattern.replaceAllIn(string, matched =>
      matched.group(0).substring(1).toUpperCase
    )

  @main def regex(): Unit = {
    val srcString = "print_all"
    val result = snakeToCamel(srcString)
    println(result)
  }
}
