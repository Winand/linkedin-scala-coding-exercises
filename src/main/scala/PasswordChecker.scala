object PasswordChecker {
  val punct = ".,;:?!\"_()"
  def isValidPassword(password: String): Boolean = {
    val hasPunct = password.exists(c => punct.contains(c))
    val hasDigits = password.exists(_.isDigit)
    val hasUppercase = password.exists(_.isUpper)
    val lenCondition = password.length >= 8
    lenCondition & hasPunct & hasDigits & hasUppercase
  }

  def main(args: Array[String]): Unit = {
    println(isValidPassword("1234"))
    println(isValidPassword("Qwerty123!!"))
  }
}
