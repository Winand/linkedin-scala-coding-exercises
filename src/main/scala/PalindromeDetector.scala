object PalindromeDetector {
  def isPalindrome(word: String): Boolean = {
    val lc = word.toLowerCase
    val leftPart = lc.substring(0, lc.length / 2)
    val rightPart = lc.substring((lc.length + 1) / 2).reverse
    leftPart == rightPart
  }

  def isPalindromeV2(word: String): Boolean = {
    val lower = word.toLowerCase
    lower == lower.reverse
  }

  def main(args: Array[String]): Unit = {
    println(s"Akka - ${isPalindrome("Akka")}")
    println(s"canoe - ${isPalindrome("canoe")}")
    println(s"bumpy - ${isPalindrome("bumpy")}")
    println(s"lighter - ${isPalindrome("lighter")}")
    println(s"radar - ${isPalindrome("radar")}")
    println(s"level - ${isPalindrome("level")}")
    println(s"kayak - ${isPalindrome("kayak")}")

    println(s"tacocat - ${isPalindrome("tacocat")}")
    println(s"Tacocat - ${isPalindrome("Tacocat")}")
    println(s"TACOCAT - ${isPalindrome("TACOCAT")}")
  }
}
