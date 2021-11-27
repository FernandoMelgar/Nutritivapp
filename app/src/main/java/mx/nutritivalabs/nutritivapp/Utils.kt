package mx.nutritivalabs.nutritivapp

import android.os.Build
import androidx.annotation.RequiresApi
import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

fun Date.simpleDateFormat(): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy")
    return formatter.format(this)
}

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDate.simpleFormat(): String {
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    return this.format(formatter);
}

fun String.asDate(): Date? {
    val formatter = SimpleDateFormat("dd/MM/yyyy")
    return formatter.parse(this)

}

fun JSONObject.toMap(): Map<String, *> = keys().asSequence().associateWith {
    when (val value = this[it])
    {
        is JSONArray ->
        {
            val map = (0 until value.length()).associate { Pair(it.toString(), value[it].toString()) }
            JSONObject(map).toMap().values.toList()
        }
        is JSONObject -> value.toMap()
        JSONObject.NULL -> null
        else            -> value
    }
}