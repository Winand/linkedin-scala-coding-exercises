enum Time {
  case All
  case At(value: Int)
}

object Time {
  def fromString(input: String): Time =
    input match {
      case "*" => All
      case number => At(number.toInt)
    }
}

final case class CronLine(
                           minute: Time,
                           hour: Time,
                           day: Time,
                           month: Time,
                           dayOfWeek: Time,
                           command: String
                         )

object Cron {
  def parse(input: String): CronLine =
    val pattern = raw"(?<mm>[*\d]+) (?<hh>[*\d]+) (?<dd>[*\d]+) (?<MM>[*\d]+) (?<dw>[*\d]+) (?<cmd>.+)".r
    //val matches = pattern.findAllMatchIn(input)
    input match
      case pattern(minute, hour, day, month, dayOfWeek, command) =>
        CronLine(
          Time.fromString(minute), Time.fromString(hour), Time.fromString(day),
          Time.fromString(month), Time.fromString(dayOfWeek), command
        )

  def main(args: Array[String]): Unit = {
    println(parse("* 89 * 9 2 ls -al /root"))
  }
}
