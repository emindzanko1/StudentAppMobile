package ba.etf.rma22.projekat.data.models

fun pitanja() : List<Pitanje> {
    return listOf(
        Pitanje("PPG-Pitanje1","Naziv ulice u kojoj je smješten ETF?",
            listOf("Bulevar Meše Selimovića", "Zmaja od Bosne","Zvornička")),
        Pitanje("PPG-Pitanje2","Broj ulice u kojoj je smješten ETF?",
            listOf("bb","1","2")),
        Pitanje("PPG-Pitanje3","Godina osnivanja ETF-a?",
            listOf("1984","1970","1961")),
        Pitanje("PPG-Pitanje4","Koliko smjerova ima ETF?",
            listOf("3","4","5")),
        Pitanje("PPG-Pitanje5","Koliko godina traje Bachelor studij na ETF-u?",
            listOf("2","3","4")),

        Pitanje("TPG-Pitanje1","Koliko predmeta ima u 2. semestru?",
            listOf("6","4","5")),
        Pitanje("TPG-Pitanje2","U kojoj se parcijali radi Objektno-orijentisano programiranje iz TP-a?",
            listOf("1.","2.","Ne radi se OOP iz TP-a")),
        Pitanje("TPG-Pitanje3","IF2 je na RI-u?",
            listOf("obavezni predmet","izborni predmet","IF2 nema na RI-u")),
        Pitanje("TPG-Pitanje4","Koji predmet nosi 7 ETCS-a?",
            listOf("IM2","TP","OS")),
        Pitanje("TPG-Pitanje5","U kojem predetu se spominju Turingove mašine?",
            listOf("VIS","TP","MLTI")),

        Pitanje("DDG-Pitanje1","Koliko predmeta ima u 3. semestru?",
            listOf("4","7","6")),
        Pitanje("DDG-Pitanje2","U kojem programskom jeziku programiramo na RPR-u?",
            listOf("Kotlin","Java","C++")),
        Pitanje("DDG-Pitanje3","Koji od ovih predmeta nije izborni u 3. semestru?",
            listOf("NA","SP","DM")),
        Pitanje("DDG-Pitanje4","Koliko ETCS-a nose svi predmeti u 3. semestru?",
            listOf("6","5","7")),
        Pitanje("DDG-Pitanje5","Koji predmet je najsličniji TP-u?",
            listOf("ASP","DM","LD")),

        Pitanje("TDG-Pitanje1","Koliko predmeta ima u 4. semestru?",
            listOf("4","7","6")),
        Pitanje("TDG-Pitanje2","U kojem programskom jeziku programiramo na RMA?",
            listOf("Kotlin","Java","C++")),
        Pitanje("TDG-Pitanje3","Koji od ovih predmeta nije izborni u 4. semestru?",
            listOf("AFJ","US","CCI")),
        Pitanje("TDG-Pitanje4","Koliko imamo obaveznih predmeta u 4. semestru?",
            listOf("4","5","7")),
        Pitanje("TDG-Pitanje5","Na kojem predmetu nemamo projekat?",
            listOf("RA","RMA","OOAD")),

        Pitanje("PTG-Pitanje1","Koliko predmeta ima u 5. semestru?",
            listOf("4","7","6")),
        Pitanje("PTG-Pitanje2","U kojem programskom jeziku programirano na WT?",
            listOf("JavaScript","C","C#")),
        Pitanje("PTG-Pitanje3","Tutorijali iz RG-a počinju u kojoj sedmici?",
            listOf("1.","2.","3.")),
        Pitanje("PTG-Pitanje4","Koji predmeta nije išao ove akademske godine?",
            listOf("OIS","RMS","PJP")),
        Pitanje("PTG-Pitanje5","Na kojem predmetu nemamo projekat?",
            listOf("OOI","OIS","PWS")),

        Pitanje("TTG-Pitanje1","Koliko predmeta ima u 6. semestru?",
            listOf("4","7","6")),
        Pitanje("TTG-Pitanje2","U SI-u se radi?",
            listOf("frontend","backend","oboje")),
        Pitanje("TTG-Pitanje3","Tutorijali iz OSP-a počinju u kojoj sedmici?",
            listOf("1.","3.","2.")),
        Pitanje("TTG-Pitanje4","Na kojem predmetu imamo projekat",
            listOf("DASS","VI","ni na jednom")),
        Pitanje("TTG-Pitanje5","Na kojem predmetu nemamo projekat?",
            listOf("OSP","SI","imamo na oba")),

        Pitanje("DČG-Pitanje1","Koliko predmeta ima u 1. semestru drugog ciklusa?",
            listOf("4","5","6")),
        Pitanje("DČG-Pitanje2","Koliko godina traje drugi ciklus?",
            listOf("1","2","3")),
        Pitanje("DČG-Pitanje3","Koliko ima semestara drugi ciklus?",
            listOf("4","3","2")),


        Pitanje("TPPG-Pitanje1","Koliko predmeta ima u 4. semestru drugog ciklusa?",
            listOf("4","5","6")),
        Pitanje("TPPG-Pitanje2","Koliko godina traje treći ciklus?",
            listOf("4","3","2")),
        Pitanje("TPPG-Pitanje3","Koliko ima semestara treći ciklus?",
            listOf("8","5","7")),
    )
}