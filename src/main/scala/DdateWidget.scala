package net.xivilization.ddate

import java.util.Date
import java.util.Calendar

import android.appwidget.AppWidgetProvider
import android.appwidget.AppWidgetManager
import android.content.Context
import android.widget.RemoteViews

class DdateWidget extends AppWidgetProvider {
  override def onUpdate(context: Context, appWidgetManager: AppWidgetManager,
    appWidgetIds: Array[Int]) = {
    val updateViews = new RemoteViews(context.getPackageName(), R.layout.main)

    val cal = Calendar.getInstance()
    val ddate = new DiscordianDate(cal.getTime())

    updateViews.setTextViewText(R.id.widget_textview, constructTime(ddate))
    appWidgetManager.updateAppWidget(appWidgetIds, updateViews)

    super.onUpdate(context, appWidgetManager, appWidgetIds)
  }

  def constructTime(ddate: DiscordianDate) = {
    def translator(n: Int): String = {
      (n match {
        case 1 => "%dst"
        case 2 => "%dnd"
        case _ => "%drd"
      }) format n
    }

    if (ddate.tibsDay) {
      "Today's St. Tib's Day %d" format ddate.year
    }
    else {
      "Today is %s, the %s of %s, %s" format (
        ddate.dayNames(ddate.weekDay-1),
        translator(ddate.seasonDay),
        ddate.seasonNames(ddate.season-1),
        ddate.year)
    }
  }
}
