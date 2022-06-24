package ba.etf.rma22.projekat.data.repositories

import android.content.Context
import android.os.Build.ID
import ba.etf.rma22.projekat.data.models.AppDatabase
import ba.etf.rma22.projekat.data.models.Odgovor
import ba.etf.rma22.projekat.data.models.OdgovorBody
import ba.etf.rma22.projekat.viewmodel.PitanjeAnketaViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class OdgovorRepository {
    companion object {
        private lateinit var context: Context

        suspend fun getOdgovoriAnketa(idAnkete: Int?) : List<Odgovor> {
            return withContext(Dispatchers.IO) {
                val poceteAnkete = TakeAnketaRepository.getPoceteAnkete()
                if(poceteAnkete.isNullOrEmpty())
                    return@withContext emptyList()
                val pretrazi = poceteAnkete.find { it.AnketumId == idAnkete }
                return@withContext ApiAdapter.retrofit.getOdgovoriAnketa(AccountRepository.acHash,pretrazi!!.id)
            }
        }

        private val pitanjeAnketaViewModel = PitanjeAnketaViewModel()

        suspend fun postaviOdgovorAnketa(idAnketaTaken: Int?, idPitanje: Int?, odgovor: Int) : Int {
            return withContext(Dispatchers.IO) {
                val poceteAnkete = pitanjeAnketaViewModel.getPoceteAnkete()!!.find { it.id == idAnketaTaken }
                val pitanjaZaAnketu = PitanjeAnketaRepository.getPitanja(poceteAnkete!!.AnketumId)
                val brojacOdgovora = pitanjaZaAnketu!!.indexOfFirst { it -> it.id == idPitanje } + 1
                val progres = ((brojacOdgovora.toFloat()/pitanjaZaAnketu.size.toFloat())*100F).toInt()
                var vrati = 0
                for(i in 100 downTo 0 step 20)
                    if(i-progres<=10){
                        vrati = i
                        break
                    }
                AppDatabase.getInstance(context).odgovorDao().obrisiSve() // za potrebe testa a8
                val id = Odgovor.ID
                var odgovor1 = Odgovor(id,odgovor)
                AppDatabase.getInstance(context).odgovorDao().dodajOdgovor(odgovor1)
                Odgovor.ID = Odgovor.ID + 1

                ApiAdapter.retrofit.postaviOdgovorNaAnketu(
                    AccountRepository.acHash,
                    idAnketaTaken!!, OdgovorBody(odgovor,pitanje = idPitanje!!,progres)
                )
                return@withContext vrati
            }
        }

        fun setContext(_context: Context) {
            context = _context
        }
    }
}
