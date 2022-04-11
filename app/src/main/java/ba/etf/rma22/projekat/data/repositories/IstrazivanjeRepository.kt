package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.data.models.Istrazivanje
import ba.etf.rma22.projekat.data.models.istrazivanje


object IstrazivanjeRepository {
    fun getIstrazivanjeByGodina(godina: Int): List<Istrazivanje> {
        var listaIstrazivanje: List<Istrazivanje> = listOf()
        for(istrazivanje in getAll())
            if(istrazivanje.godina == godina)
                listaIstrazivanje+=istrazivanje
        return listaIstrazivanje
    }
    fun getAll() : List<Istrazivanje> = istrazivanje()
    fun getUpisani() : List<Istrazivanje> = KorisnikRepository.getMojaIstrazivanja()
}