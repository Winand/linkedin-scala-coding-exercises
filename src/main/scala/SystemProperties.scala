import scala.sys.SystemProperties as SysProp

object SystemProperties {
  def main(args: Array[String]): Unit =
    SysProp().iterator.foreach((key, value) =>
      println(s"$key: $value")
    )
  //    for (key, value) <- SysProp() yield {
  //      println(s"$key: $value")
  //    }
}
