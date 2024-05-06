package basketball

import basketball.Event.TwoPtMake

final case class StatLine(
                           player: String,
                           twoPointMakes: Int,
                           twoPointAttempts: Int,
                           threePointMakes: Int,
                           threePointAttempts: Int,
                           offensiveRebounds: Int,
                           defensiveRebounds: Int
                         ) {
  def addTwoPointMake: StatLine =
    this.copy(
      twoPointMakes = twoPointMakes + 1,
      twoPointAttempts = twoPointAttempts + 1
    )

  def addTwoPointAttempt: StatLine =
    this.copy(
      twoPointAttempts = twoPointAttempts + 1
    )

  def addThreePointMake: StatLine =
    this.copy(
      threePointMakes = threePointMakes + 1,
      threePointAttempts = threePointAttempts + 1
    )

  def addThreePointAttempt: StatLine =
    this.copy(
      threePointAttempts = threePointAttempts + 1
    )

  def addOffensiveRebound: StatLine =
    this.copy(offensiveRebounds = offensiveRebounds + 1)

  def addDefensiveRebound: StatLine =
    this.copy(defensiveRebounds = defensiveRebounds + 1)
}

final case class BoxScore(home: List[StatLine], away: List[StatLine])

object Basketball {
  /**
   * Calc statistics for a player.
   *
   * @param player player name
   * @param events list of player's events
   * @return
   */
  def calcPlayerStats(player: String, events: List[Event]) =
    events.foldLeft(
      StatLine(player, 0, 0, 0, 0, 0, 0)
    )((acc, event) => event match
      case Event.TwoPtMake(_, _) => acc.addTwoPointMake
      case Event.TwoPtMiss(_, _) => acc.addTwoPointAttempt
      case Event.ThreePtMake(_, _) => acc.addThreePointMake
      case Event.ThreePtMiss(_, _) => acc.addThreePointAttempt
      case Event.OffensiveRebound(_, _) => acc.addOffensiveRebound
      case Event.DefensiveRebound(_, _) => acc.addDefensiveRebound
    )

  /**
   * Calc event statistics for each player in a team.
   *
   * @param events list of all events
   * @return List[StatLine]
   */
  def calcTeamStats(team: String, events: Map[String, List[Event]]): List[StatLine] =
    events(team).groupBy(_.player).map(calcPlayerStats).toList

  def boxScore(
                home: String,
                away: String,
                events: List[Event]
              ): BoxScore =
    val eventsByTeam = events.groupBy(_.team)
    val homeStats = calcTeamStats(home, eventsByTeam)
    val awayStats = calcTeamStats(away, eventsByTeam)
    BoxScore(homeStats, awayStats)

  // Run this to see an example
  @main def exampleGame(): Unit = {
    val events =
      BasketballGenerator
        .gameEvents(
          120,
          BasketballGenerator.birmingham,
          BasketballGenerator.highbury
        )
        .run
    val boxScore = Basketball.boxScore(
      BasketballGenerator.birmingham.name,
      BasketballGenerator.highbury.name,
      events
    )
    println(boxScore)
  }
}
