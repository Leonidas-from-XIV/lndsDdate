/*
 * Discordian date in Scala
 *
 * Algorithm from
 * <http://imrannazar.com/Discordian-Date-Handling-in-Java>
 *
 */

package net.xivilization.ddate

import java.util.Date
import java.util.Calendar
import java.util.GregorianCalendar

class DiscordianDate(date: Date) {
  val seasonNames = Array(
    "Chaos", "Discord", "Confusion", "Bureucracy", "The Aftermath")
  val dayNames = Array(
    "Sweetmorn", "Boomtime", "Pungenday", "Prickle-Prickle", "Setting Orange")

  val baseCalendar: GregorianCalendar = new GregorianCalendar()
  baseCalendar.setTime(date)
  val year = baseCalendar.get(Calendar.YEAR) + 1166
  val yearDay = baseCalendar.get(Calendar.DAY_OF_YEAR)
  val isLeap = baseCalendar.isLeapYear(baseCalendar.get(Calendar.YEAR))

  val yd = if (isLeap && yearDay > 60) yearDay - 2 else yearDay - 1

  val season = (yd / 73) + 1
  val weekDay = (yd % 5) + 1
  val seasonDay = (yd % 73) + 1

  val seasonName = seasonNames(season-1)
  val dayName = dayNames(yearDay % 5)
  val tibsDay = isLeap && yearDay == 59

  override def toString(): String = {
    if (tibsDay) {
      return "St. Tib's Day, " + year.toString()
    }
    else {
      return dayNames(weekDay-1) + ", " +
        seasonNames(season-1) + " " +
        seasonDay.toString() + ", " +
        year.toString()
    }
  }

  def getTime(): Date = {
    var sentinelCalendar = new GregorianCalendar()
    sentinelCalendar.set(Calendar.YEAR, year - 1166)
    sentinelCalendar.set(Calendar.DAY_OF_YEAR, yearDay)

    return sentinelCalendar.getTime()
  }
}
