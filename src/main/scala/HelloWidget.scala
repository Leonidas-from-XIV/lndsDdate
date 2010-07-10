package net.xivilization.ddate;

import _root_.android.appwidget.AppWidgetProvider;
import _root_.android.appwidget.AppWidgetManager;
import _root_.android.content.Context;
import _root_.android.widget.RemoteViews;

class HelloWidget extends AppWidgetProvider {
  override def onUpdate(context: Context, appWidgetManager: AppWidgetManager,
    appWidgetIds: Array[Int]) = {
    val updateViews = new RemoteViews(context.getPackageName(), R.layout.main);
    updateViews.setTextViewText(R.id.widget_textview, "Foo");
    appWidgetManager.updateAppWidget(appWidgetIds, updateViews);

    super.onUpdate(context, appWidgetManager, appWidgetIds);
  }
}
