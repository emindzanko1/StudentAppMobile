package ba.etf.rma22.projekat.viewmodel

import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.models.Istrazivanje
import ba.etf.rma22.projekat.data.models.grupe
import ba.etf.rma22.projekat.data.models.istrazivanje
import ba.etf.rma22.projekat.data.repositories.IstrazivanjeRepository
import ba.etf.rma22.projekat.data.repositories.KorisnikRepository

class UpisIstrazivanjeViewModel {
    fun getIstrazivanjeByGodina(godina:Int) : List<Istrazivanje>{
        return IstrazivanjeRepository.getIstrazivanjeByGodina(godina)
    }
    fun getAll() : List<Istrazivanje>{
        return IstrazivanjeRepository.getAll()
    }
    fun getUpisani() : List<Istrazivanje>{
        return IstrazivanjeRepository.getUpisani()
    }
    fun getGroupsByIstrazivanje(nazivIstrazivanja : String) : List<Grupa>{
        var listaGrupa : List<Grupa> = listOf()
        for(grupa in grupe())
            if(grupa.nazivIstrazivanja == nazivIstrazivanja)
                listaGrupa += grupa
        return listaGrupa
    }
    fun upisiIstrazivanje(godinaIstrazivanja: String, nazivIstrazivanja: String, nazivGrupe: String){
        KorisnikRepository.upisiIstrazivanje(Istrazivanje(nazivIstrazivanja,godinaIstrazivanja.toInt()))
        KorisnikRepository.upisiGrupu(Grupa(nazivGrupe,nazivIstrazivanja))
    }
}