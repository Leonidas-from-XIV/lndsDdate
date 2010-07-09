import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

class DiscordianDate(date: Date) {
  var seasonNames = {
    "Chaos"; "Discord"; "Confusion"; "Bureucracy"; "The Aftermath"};
  var dayNames = {
    "Sweetmorn"; "Boomtime"; "Pungenday"; "Prickle-Prickle"; "Setting Orange"};


  var baseCalendar: GregorianCalendar = new GregorianCalendar();
  baseCalendar.setTime(date);
  var year = baseCalendar.get(Calendar.YEAR) + 1166;
  var yearDay = baseCalendar.get(Calendar.DAY_OF_YEAR);
  var isLeap = baseCalendar.isLeapYear(baseCalendar.get(Calendar.YEAR));
  var yd = yearDay - 1;
  if (isLeap && yd > 59)
    yd = yd - 1;

  var season = (yd / 73) + 1;
  var weekDay = (yd % 5) + 1;
  var seasonDay = (yd % 73) + 1;
}
