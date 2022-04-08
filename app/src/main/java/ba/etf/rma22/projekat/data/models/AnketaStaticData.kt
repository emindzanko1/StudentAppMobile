package ba.etf.rma22.projekat.data.models

import java.util.*

private fun calendarToDate(year: Int, month: Int, date: Int) : Date {
    var cal: Calendar = Calendar.getInstance()
    cal.set(year, month, date)
    var date: Date = cal.time;
    return date
}

fun anketa() : List<Anketa> {
    return listOf(
        Anketa(
            "Anketa 1", "Istraživanje broj: 1",   calendarToDate(2021, 6, 11),
            calendarToDate(2021, 6, 29), null, "RI3", 0.11F
        ),
        Anketa(
            "Anketa 2", "Istraživanje broj: 2",
            calendarToDate(2022, 2, 22),
            calendarToDate(2022, 3, 22),
            calendarToDate(2022, 3, 18), "RI4", 0.21F,
        ),
        Anketa(
            "Anketa 3", "Istraživanje broj: 3",
            calendarToDate(2022, 2, 24),
            calendarToDate(2022, 3, 24),
            calendarToDate(2022, 3, 18), "RI1.2", 0.31F,
        ),
        Anketa(
            "Anketa 4", "Istraživanje broj: 4",
            calendarToDate(2022, 10, 9),
            calendarToDate(2022, 10, 10), null, "RI3", 0.41F,
        ),
        Anketa(
            "Anketa 5", "Istraživanje broj: 5",
            calendarToDate(2022, 1, 24),
            calendarToDate(2022, 2, 24), null, "RI2", 0.51F,
        ),
        Anketa(
            "Anketa 6", "Istraživanje broj: 6",
            calendarToDate(2022, 1, 1),
            calendarToDate(2022, 4, 3), calendarToDate(2022, 2, 7), "RI2", 0.71F
        ),
        Anketa(
            "Anketa 7", "Istraživanje broj: 7",   calendarToDate(2021, 7, 15),
            calendarToDate(2021, 7, 29), null, "Prva grupa", 0.11F
        ),
        Anketa(
            "Anketa 8", "Istraživanje broj: 8",
            calendarToDate(2022, 6, 22),
            calendarToDate(2022, 8, 8),
            calendarToDate(2022, 7, 18), "RI1.3", 0.21F,
        ),
        Anketa(
            "Anketa 9", "Istraživanje broj: 9",
            calendarToDate(2021, 2, 24),
            calendarToDate(2021, 3, 24),
            calendarToDate(2021, 3, 18), "8", 0.31F,
        ),
        Anketa(
            "Anketa 10", "Istraživanje broj: 10",
            calendarToDate(2021, 10, 9),
            calendarToDate(2021, 10, 10), null, "RI1.4", 0.41F,
        ),
        Anketa(
            "Anketa 11", "Istraživanje broj: 11",
            calendarToDate(2022, 10, 24),
            calendarToDate(2022, 12, 24), null, "RI1.1", 0.51F,
        ),
        Anketa(
            "Anketa 12", "Istraživanje broj: 12",
            calendarToDate(2022, 3, 5),
            calendarToDate(2022, 4, 7), calendarToDate(2022, 5, 7), "RI3", 0.71F
        ),
    )
}