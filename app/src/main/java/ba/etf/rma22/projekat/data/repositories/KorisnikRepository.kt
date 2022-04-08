package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.models.Istrazivanje

object KorisnikRepository {

    private lateinit var mojaIstrazivanja: List<Istrazivanje>;
    private lateinit var mojeGrupe: List<Grupa>;

    init{
        mojaIstrazivanja = listOf(Istrazivanje("Istraživanje broj: 1",3), Istrazivanje("Istraživanje broj: 6",2),
            Istrazivanje("Istraživanje broj: 8",1), Istrazivanje("Istraživanje broj: 11", 1)
        )
        mojeGrupe = listOf(Grupa("RI3","Istraživanje broj: 1"), Grupa("RI2","Istraživanje broj: 6"),
            Grupa("RI1.3","Istraživanje broj: 8"),Grupa("RI1.1","Istraživanje broj: 11"))
    }

    private fun daLiJeMojaAnketa(anketa: Anketa) : Boolean{
        for(grupa in mojeGrupe)
            if(grupa.naziv == anketa.nazivGrupe && grupa.nazivIstrazivanja == anketa.nazivIstrazivanja)
                return true;
        return false;
    }

    fun getMojeAnkete() : List<Anketa> {
        var listaAnekta : List<Anketa> = listOf()
        for(anketa in AnketaRepository.getAll())
            if(daLiJeMojaAnketa(anketa))
                listaAnekta+=anketa
        return listaAnekta
    }
}