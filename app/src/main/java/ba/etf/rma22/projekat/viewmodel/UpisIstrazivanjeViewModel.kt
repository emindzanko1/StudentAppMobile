package ba.etf.rma22.projekat.viewmodel

import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.models.Istrazivanje
import ba.etf.rma22.projekat.data.repositories.IstrazivanjeIGrupaRepository

class UpisIstrazivanjeViewModel {

    suspend fun upisiUGrupu(idGrupa: Int): Boolean = IstrazivanjeIGrupaRepository.upisiUGrupu(idGrupa)

    suspend fun getIstrazivanjaZaAnketu(anketaId: Int) : List<Istrazivanje> = IstrazivanjeIGrupaRepository.getIstrazivanjaZaAnketu(anketaId)

    suspend fun getIstrazivanjaByGodina(godina: Int) : List<Istrazivanje> = IstrazivanjeIGrupaRepository.getIstrazivanjaByGodina(godina)

    suspend fun getIstrazivanjeByNaziv(nazivIstrazivanja: String) : Istrazivanje? = IstrazivanjeIGrupaRepository.getIstrazivanjeByNaziv(nazivIstrazivanja)

    suspend fun getGrupaZaIstrazivanje(idIstrazivanja : Int, nazivGrupe: String) : Grupa? = IstrazivanjeIGrupaRepository.getGrupaZaIstrazivanje(idIstrazivanja,nazivGrupe)

    suspend fun upisiUGrupuBaza(grupa: Grupa) = IstrazivanjeIGrupaRepository.upisiUGrupuBaza(grupa)
}