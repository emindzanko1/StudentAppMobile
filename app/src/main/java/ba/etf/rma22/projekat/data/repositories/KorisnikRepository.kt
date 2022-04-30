package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.models.Istrazivanje

object KorisnikRepository {

    private var mojaIstrazivanja: ArrayList<Istrazivanje> =
        arrayListOf(Istrazivanje("PPG",1), Istrazivanje("TDG",2),
            Istrazivanje("PTG",3), Istrazivanje("DČG", 4)
        )

    private var mojeGrupe: ArrayList<Grupa> =
        arrayListOf(Grupa("PPG-grupa2","PPG"), Grupa("TDG-grupa3","TDG"),
            Grupa("PTG-grupa2","PTG"),Grupa("DČG-grupa3","DČG"))

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

    fun getMojaIstrazivanja() : List<Istrazivanje> = mojaIstrazivanja

    fun upisiIstrazivanje(istrazivanje: Istrazivanje) = mojaIstrazivanja.add(istrazivanje)

    fun upisiGrupu(grupa: Grupa) = mojeGrupe.add(grupa)
}