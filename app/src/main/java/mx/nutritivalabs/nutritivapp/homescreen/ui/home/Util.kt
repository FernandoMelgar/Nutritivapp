package mx.nutritivalabs.nutritivapp.homescreen.ui.home

import java.util.*

fun createDateStringFromTodayTo(n: Int): MutableList<String> {
    val listOfDays = mutableListOf<String>()
    val today = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
    for (i in 0..n)
        listOfDays.add((today + i).toString())
    return listOfDays
}