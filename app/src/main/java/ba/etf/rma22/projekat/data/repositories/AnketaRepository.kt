package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.anketa
import java.util.*

object AnketaRepository {
    fun getMyAnkete() : List<Anketa> = KorisnikRepository.getMojeAnkete()
    fun getAll() : List<Anketa> {
        return anketa()
    }
    fun getDone() : List<Anketa> {
        var listaAnekta : List<Anketa> = listOf()
        var danasnjiDatum : Date = Date()
        for(anketa in getMyAnkete())
            if(anketa.datumRada != null && anketa.datumPocetka.before(anketa.datumRada) && anketa.datumKraja.after(anketa.datumRada)
                && danasnjiDatum > anketa.datumRada)
                listaAnekta+=anketa
        return listaAnekta
    }
    fun getFuture() : List<Anketa> {
        var listaAnekta : List<Anketa> = listOf()
        val danasnjiDatum : Date = Date()
        for(anketa in getMyAnkete())
            if(anketa.datumPocetka.after(danasnjiDatum) || (anketa.datumRada != null && anketa.datumPocetka.before(anketa.datumRada) && anketa.datumKraja.after(anketa.datumRada)
                && danasnjiDatum < anketa.datumRada))
                listaAnekta+=anketa
        return listaAnekta
    }
    fun getNotTaken() : List<Anketa> {
        var listaAnekta : List<Anketa> = listOf()
        val danasnjiDatum : Date = Date()
        for(anketa in getMyAnkete())
                if(anketa.datumRada == null && danasnjiDatum.after(anketa.datumKraja))
                        listaAnekta+=anketa
        return listaAnekta
    }
}