package com.ahmaddudayef.footballclub.utils

import android.content.Context
import android.content.Intent
import android.provider.CalendarContract
import android.widget.Toast
import com.ahmaddudayef.footballclub.data.network.model.schedule.EventsItem
import org.jetbrains.anko.toast
import java.text.SimpleDateFormat

/**
 * Created by Ahmad Dudayef on 10/22/2018.
 */
object ReminderUtil {

    fun getGoogleCalendarId(activity: Context?): Long {
        val projection = arrayOf(CalendarContract.Calendars._ID, CalendarContract.Calendars.NAME, CalendarContract.Calendars.ACCOUNT_NAME, CalendarContract.Calendars.ACCOUNT_TYPE)
        val calCursor = activity?.contentResolver
                ?.query(CalendarContract.Calendars.CONTENT_URI, projection,
                        CalendarContract.Calendars.VISIBLE + " = 1", null, CalendarContract.Calendars._ID + " ASC")
        if (calCursor!!.moveToFirst()) {
            do {
                val id = calCursor.getLong(0)
                return id
            } while (calCursor.moveToNext())
        }
        return -1

    }

    fun addEventToGoogleCalendar(activity: Context?, match: EventsItem) {
        if(match.intHomeScore != null) activity?.toast("This event has passed, please choose the upcoming one!")

        val calId = getGoogleCalendarId(activity)

        if (calId == -1L) {
            Toast.makeText(activity, "Somethings went wrong, try again!", Toast.LENGTH_SHORT).show()
            return
        }

        val title = match.strEvent
        val clock = match.strTime?.split("+")?.get(0)
        val dt = match.dateEvent
        val dateWithClock = "$dt $clock"
        val simpleDate = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
        val date = simpleDate.parse(dateWithClock)

        val timeInMillis = date.time

        //add end time to 90 minutes
        val end = timeInMillis + 5400000
        val intent = Intent(Intent.ACTION_EDIT)
        intent.type = "vnd.android.cursor.item/event"
        intent.putExtra(CalendarContract.Events.TITLE, title)
        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, timeInMillis)
        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, end)
        intent.putExtra(CalendarContract.Events.EVENT_LOCATION, "Television")
        activity?.startActivity(intent)
    }

}