package net.xivilization.ddate

import java.util.Date
import java.util.Calendar

import _root_.android.appwidget.AppWidgetProvider
import _root_.android.appwidget.AppWidgetManager
import _root_.android.content.Context
import _root_.android.widget.RemoteViews

class HelloWidget extends AppWidgetProvider {
  override def onUpdate(context: Context, appWidgetManager: AppWidgetManager,
    appWidgetIds: Array[Int]) = {
    val updateViews = new RemoteViews(context.getPackageName(), R.layout.main)

    val cal = Calendar.getInstance()
    val ddate = new DiscordianDate(cal.getTime())

    updateViews.setTextViewText(R.id.widget_textview, constructTime(ddate))
    appWidgetManager.updateAppWidget(appWidgetIds, updateViews)

    super.onUpdate(context, appWidgetManager, appWidgetIds)
  }

  def constructTime(ddate: DiscordianDate): String = {
    def translator(n: Int): String = {
      return (n match {
        case 1 => "%dst"
        case 2 => "%dnd"
        case _ => "%drd"
      }) format n
    }

    if (ddate.tibsDay) {
      return "Today's St. Tib's Day %d" format ddate.year
    }
    else {
      return "Today is %s, the %s of %s, %s" format (
        ddate.dayNames(ddate.weekDay-1),
        translator(ddate.seasonDay),
        ddate.seasonNames(ddate.season-1),
        ddate.year)
    }
  }
}
