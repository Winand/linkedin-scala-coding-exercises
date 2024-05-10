import RocketShip.State.Flying
import cats.effect.unsafe.implicits.global
import doodle.image.Image
import doodle.core.*
import doodle.syntax.all.*
//import doodle.image.syntax.all.*
import doodle.reactor.Reactor
import doodle.java2d.*

object RocketShip {
  // Defines the outline of the rocket ship
  val shipOutline = {
    import PathElement.*
    val leftSide = List(moveTo(0, 0), curveTo(-20, -30, -40, -60, -30, -120))
    val bottom = List(curveTo(-15, -100, 15, -100, 30, -120))
    val rightSide = List(curveTo(40, -60, 20, -30, 0, 0))
    Image.path(ClosedPath(leftSide ++ bottom ++ rightSide))
  }

  // Create an image for the portholes, given an alpha (value for transparency)
  def portholes(alpha: Normalized) =
    Image
      .circle(10)
      .at(0, -25)
      .on(Image.circle(30).at(0, -55))
      .on(Image.circle(10).at(0, -85))
      .fillColor(Color.white.alpha(alpha))

  // Create an image for the ship, given an alpha (value for transparency)
  def ship(alpha: Normalized) =
    portholes(alpha)
      .on(shipOutline.fillColor(Color.crimson.alpha(alpha)))
      .strokeColor(Color.black.alpha(alpha))
      .strokeWidth(5.0)

  // Create the image of the ship during the countdown (fading in)
  def drawCountdown(count: Int): Image = {
    val alpha = ((100 - count).toDouble / 100.0).normalized
    ship(alpha).at(startingPoint).above(Image.text(count.toString))
  }

  // Create the image of the ship flying (moving up the screen)
  def drawFlying(location: Point): Image = {
    val loc = if (location.y < 0) location else Point(0, location.y + 130) // Smooth movement fix
    ship(Normalized.MaxValue).at(loc) //.below(Image.text(loc.y.toString))
  }

  // The State is either
  // - Countdown, containing an integer, the counts left
  // - Flying, containing a Point, the location of the rocket ship
  enum State:
    case Countdown(count: Int)
    case Flying(point: Point)

  // The place the ship starts at
  val startingPoint = Point(0, -280)
  // The velocity that the ship moves with
  val velocity = Vec(0, 10)

  // If the state is Countdown, and the count is zero, the result should be a
  // Flying ship, starting at startingPoint. Otherwise the result should be
  // Countdown with the count decremented by one.
  //
  // If the state is Flying, the result if Flying with a position equal to the
  // current position plus velocity.
  def nextState(state: State): State =
    import State.*
    state match
      case Countdown(count) =>
        if (count == 0) Flying(startingPoint)
        else Countdown(count - 1)
      case Flying(point) =>
        Flying(point + velocity)

  // Given the state, create the image using drawCountdown and drawFlying above.
  def draw(state: State): Image =
    state match
      case State.Countdown(count) => drawCountdown(count)
      case State.Flying(point) => drawFlying(point)

  // Is true when the ship is Flying and location's y value is >= 540
  def finished(state: State): Boolean =
    state match {
      case Flying(point) => point.y >= 540
      case _ => false
    }

  val animation =
    Reactor
      .init(
        // The initial state should be Countdown with 100 counts left.
        // If you followed the specification to the letter this should just work.
        State.Countdown(10)
      )
      .withOnTick(nextState)
      .withRender(draw)
      .withStop(finished)

  // Run this to see the rocket ship take off!
  @main def toTheStars =
    // FIXME: animation is not shown w/o image draw
    import doodle.image.syntax.all.ImageOps
    Image.empty.draw()

    animation.run(Frame.default.withSize(200, 800).withBackground(Color.midnightBlue))
}
