package duarte.diego.financeapp.commons.extensions

import java.text.ParseException
import java.text.ParsePosition
import java.text.SimpleDateFormat
import java.util.*

fun String.toDate(format: String = "dd/MM/yyyy"): Date {
    val sdf = SimpleDateFormat(format, Locale("pt", "BR"))
    return sdf.parse(this)
}

fun Date.format(format: String): String = SimpleDateFormat(
    format,
    Locale("pt", "BR")
).format(this)

fun String.formatToStatement() = toDate("MM/yyyy").format(
when (Calendar.getInstance().time.getYearLastDigits() == toDate("MM/yyyy").getYearLastDigits()) {
    true -> "MMM"
    false -> "MMM/yy"
}
).uppercase(Locale.getDefault()).replace(".", "")

fun Date.formatToStatement() = format(
when (Calendar.getInstance().time.getYearLastDigits() == this.getYearLastDigits()) {
    true -> "MMM"
    false -> "MMM/yy"
}
).uppercase(Locale.getDefault()).replace(".", "")

fun Date.formatToStatementSubtitle() = "${this.format("dd")}/${
    this.format("MMM")
        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
}".replace(".", "")

@Throws(NumberFormatException::class)
fun Date.getMonthNumber() = format("MM").toInt()

@Throws(NumberFormatException::class)
fun Date.getMonthName() = format("MMM").uppercase()

@Throws(NumberFormatException::class)
fun Date.getDayInMonth() = format("dd").toInt()

@Throws(NumberFormatException::class)
fun Date.getYearNumber() = format("yyyy").toInt()

@Throws(NumberFormatException::class)
fun Date.getYearLastDigits() = format("yy").toInt()

fun todayDate(): Date {
    val c = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }
    val millis = (System.currentTimeMillis() - c.timeInMillis)
    return Date(millis)
}

fun Date.addDays(days: Int): Date {
    val c = Calendar.getInstance()
    c.time = this
    c.add(Calendar.DATE, +days)
    return c.time
}

fun Date.subtractDays(days: Int): Date {
    val c = Calendar.getInstance()
    c.time = this
    c.add(Calendar.DATE, -days)
    return c.time
}

fun Date.subtractMonths(months: Int): Date {
    val c = Calendar.getInstance()
    c.time = this
    c.add(Calendar.MONTH, -months)
    return c.time
}

fun Date.isHoliday(holiday: List<String>): Boolean {
    holiday.map {
        if (this == it.toDate()) {
            return true
        }
    }
    return false
}

fun Date.isWeekend(): Boolean {
    return this.isSaturday() || this.isSunday()
}

fun Date.isFriday(): Boolean {
    val calendar = Calendar.getInstance()
    calendar.time = this
    val dayOfWeek = calendar[Calendar.DAY_OF_WEEK]
    return dayOfWeek == Calendar.FRIDAY
}

fun Date.isSaturday(): Boolean {
    val calendar = Calendar.getInstance()
    calendar.time = this
    val dayOfWeek = calendar[Calendar.DAY_OF_WEEK]
    return dayOfWeek == Calendar.SATURDAY
}

fun Date.isSunday(): Boolean {
    val calendar = Calendar.getInstance()
    calendar.time = this
    val dayOfWeek = calendar[Calendar.DAY_OF_WEEK]
    return dayOfWeek == Calendar.SUNDAY
}

fun Date.subtractYears(years: Int): Date = Calendar.getInstance().apply {
    time = this@subtractYears
    add(Calendar.YEAR, -years)
}.time

fun Date.beforeOrEquals(date: Date) = date.time >= this.time

fun Date.afterOrEquals(date: Date) = date.time <= this.time

fun getCurrentDate(): Date = Calendar.getInstance().time

fun getCurrentDateFormatted(): String = getCurrentDate().format("dd/MM/yyyy")

fun Date.setDateLastHour(): Date {
    val date = this
    val calendar = Calendar.getInstance().apply {
        time = date
        this.set(Calendar.HOUR_OF_DAY, 23)
        this.set(Calendar.MINUTE, 59)
        this.set(Calendar.SECOND, 59)
    }
    return calendar.time
}

private val DATE_FORMAT_STRINGS = arrayOf(
    // HTTP formats required by RFC2616 but with any timezone.
    "EEE MMM d HH:mm:ss z yyyy",
    "EEE, dd MMM yyyy HH:mm:ss zzz", // RFC 822, updated by RFC 1123 with any TZ
    "EEEE, dd-MMM-yy HH:mm:ss zzz", // RFC 850, obsoleted by RFC 1036 with any TZ.
    "EEE MMM d HH:mm:ss yyyy", // ANSI C's asctime() format
    // Alternative formats.
    "EEE, d MMM yyyy HH:mm:ss Z",
    "EEE, dd-MMM-yyyy HH:mm:ss z",
    "EEE, dd-MMM-yyyy HH-mm-ss z",
    "EEE, dd MMM yy HH:mm:ss z",
    "EEE dd-MMM-yyyy HH:mm:ss z",
    "EEE dd MMM yyyy HH:mm:ss z",
    "EEE dd-MMM-yyyy HH-mm-ss z",
    "EEE dd-MMM-yy HH:mm:ss z",
    "EEE dd MMM yy HH:mm:ss z",
    "EEE,dd-MMM-yy HH:mm:ss z",
    "EEE,dd-MMM-yyyy HH:mm:ss z",
    "EEE, dd-MM-yyyy HH:mm:ss z",

    /* RI bug 6641315 claims a cookie of this format was once served by www.yahoo.com */
    "EEE MMM d yyyy HH:mm:ss z"
)

fun String.toDateOrNull(format: String) = try {
    toDate(format)
} catch (e: ParseException) {
    null
}

fun String.formatToDateOrNull(): Date? {
    if (isBlank()) return null

    val position = ParsePosition(0)

    DATE_FORMAT_STRINGS.forEach {
        val format = SimpleDateFormat(it, Locale.US).apply {
            isLenient = false
            this.timeZone = TimeZone.getTimeZone("GMT-3")
        }

        try {
            val result = format.parse(this, position)
            if (position.index != 0) return result
        } catch (ignored: Exception) {
        }
    }
    return null
}