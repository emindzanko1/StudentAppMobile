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
            "Anketa 1", "PPG",   calendarToDate(2021, 6, 11),
            calendarToDate(2021, 6, 29), null, "PPG-grupa2", 0.11F
        ),
        Anketa(
            "Anketa 2", "TPG",
            calendarToDate(2022, 2, 22),
            calendarToDate(2022, 3, 22),
            calendarToDate(2022, 3, 18), "TPG-grupa1", 0.21F,
        ),
        Anketa(
            "Anketa 3", "DDG",
            calendarToDate(2022, 1, 24),
            calendarToDate(2022, 2, 24),
            calendarToDate(2022, 2, 18), "DDG-grupa1", 0.36F,
        ),
        Anketa(
            "Anketa 4", "DDG",
            calendarToDate(2022, 10, 9),
            calendarToDate(2022, 10, 10), null, "DDG-grupa2", 0.41F,
        ),
        Anketa(
            "Anketa 5", "TDG",
            calendarToDate(2022, 1, 24),
            calendarToDate(2022, 2, 24), null, "TDG-grupa2", 0.51F,
        ),
        Anketa(
            "Anketa 6", "TDG",
            calendarToDate(2022, 1, 1),
            calendarToDate(2022, 4, 3), calendarToDate(2022, 2, 7), "TDG-grupa3", 1F
        ),
        Anketa(
            "Anketa 7", "PTG",   calendarToDate(2021, 7, 15),
            calendarToDate(2021, 7, 29), null, "PTG-grupa1", 0.11F
        ),
        Anketa(
            "Anketa 8", "PTG",
            calendarToDate(2022, 6, 22),
            calendarToDate(2022, 8, 8),
            calendarToDate(2022, 7, 18), "PTG-grupa2", 0.26F,
        ),
        Anketa(
            "Anketa 9", "TTG",
            calendarToDate(2021, 2, 24),
            calendarToDate(2021, 3, 24),
            calendarToDate(2021, 3, 18), "TTG-grupa3", 1F,
        ),
        Anketa(
            "Anketa 10", "DČG",
            calendarToDate(2021, 10, 9),
            calendarToDate(2021, 10, 10), null, "DČG-grupa1", 0.41F,
        ),
        Anketa(
            "Anketa 11", "DČG",
            calendarToDate(2022, 7, 24),
            calendarToDate(2022, 8, 24), null, "DČG-grupa3", 0.51F,
        ),
        Anketa(
            "Anketa 12", "TPPG",
            calendarToDate(2022, 3, 5),
            calendarToDate(2022, 6, 7), calendarToDate(2022, 5, 7), "TPPG-grupa2", 0.71F
        )
    )
}