package ba.etf.rma22.projekat.viewmodel

import ba.etf.rma22.projekat.data.models.AnketaTaken
import ba.etf.rma22.projekat.data.models.Odgovor
import ba.etf.rma22.projekat.data.models.Pitanje
import ba.etf.rma22.projekat.data.repositories.OdgovorRepository
import ba.etf.rma22.projekat.data.repositories.PitanjeAnketaRepository
import ba.etf.rma22.projekat.data.repositories.TakeAnketaRepository

class PitanjeAnketaViewModel {

    suspend fun getPoceteAnkete(): List<AnketaTaken>? = TakeAnketaRepository.getPoceteAnkete()

    suspend fun getPitanja(idAnkete: Int): List<Pitanje> = PitanjeAnketaRepository.getPitanja(idAnkete)!!

    suspend fun getOdgovoriAnketa(idAnketa: Int): List<Odgovor> = OdgovorRepository.getOdgovoriAnketa(idAnketa)

}
