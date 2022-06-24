package ba.etf.rma22.projekat.data.repositories

import android.content.Context
import ba.etf.rma22.projekat.data.models.AppDatabase
import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.models.Istrazivanje
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class IstrazivanjeIGrupaRepository {
    companion object {
        private lateinit var context: Context

        suspend fun getIstrazivanja(offset: Int) : List<Istrazivanje>? {
            return withContext(Dispatchers.IO){
                val response = ApiAdapter.retrofit.getIstrazivanja(offset)
                upisiIstrazivanjaUBazu(response.body()!!)
                return@withContext response.body()
            }
        }

        suspend fun upisiIstrazivanjaUBazu(istrazivanja : List<Istrazivanje>) : String? {
            return withContext(Dispatchers.IO) {
                try{
                    for(istrazivanje in istrazivanja)
                        AppDatabase.getInstance(context).istrazivanjeDao().dodajIstrazivanje(istrazivanje)
                    return@withContext "success"
                }
                catch (error: Exception){
                    return@withContext null
                }
            }
        }

        suspend fun getIstrazivanja() : List<Istrazivanje> {
            return withContext(Dispatchers.IO){
                val listaIstrazivanja = ArrayList<Istrazivanje>()
                var brojac = 0
                while(true){
                    val lista = getIstrazivanja(brojac)
                    if(lista != null)
                        listaIstrazivanja.addAll(lista)
                    brojac++
                    if(brojac == 5)
                        break
                }
                return@withContext listaIstrazivanja
            }
        }

        suspend fun getGrupe() : List<Grupa>? {
            return withContext(Dispatchers.IO){
                val response = ApiAdapter.retrofit.getGrupe()
                upisiGrupuUBazu(response.body()!!)
                return@withContext response.body()
            }
        }

        suspend fun upisiGrupuUBazu(grupe: List<Grupa>) : String? {
            return withContext(Dispatchers.IO){
                try{
                    for(grupa in grupe)
                        AppDatabase.getInstance(context).grupaDao().dodajGrupu(grupa)
                    return@withContext "success"
                }
                catch (error: Exception){
                    return@withContext null
                }
            }
        }

        suspend fun getGrupeZaIstrazivanje(idIstrazivanja : Int) : List<Grupa> {
            return withContext(Dispatchers.IO){

                val listaGrupa = ArrayList<Grupa>()

                for(grupa in getGrupeZaIstrazivanje(idIstrazivanja))
                    if(grupa.id == idIstrazivanja)
                        listaGrupa.add(grupa)

                return@withContext listaGrupa
            }
        }

        suspend fun upisiUGrupu(idGrupa : Int?) : Boolean {
            return withContext(Dispatchers.IO){
                val response = ApiAdapter.retrofit.upisiStudentaUGrupu(idGrupa!!,AccountRepository.acHash)
                return@withContext response.message().contains("je dodan u grupu")
            }
        }

        suspend fun getUpisaneGrupe() : List<Grupa>? {
            return withContext(Dispatchers.IO) {
                val response = ApiAdapter.retrofit.getUpisaneGrupe(AccountRepository.acHash)
                return@withContext response.body() ?: return@withContext emptyList<Grupa>()
            }
        }

        suspend fun upisiUGrupuBaza(grupa: Grupa) {
            withContext(Dispatchers.IO) {
                //upisiUGrupu(grupa.id)
                //AppDatabase.getInstance(context).grupaDao().dodajGrupu(grupa)
                println(grupa.naziv)
                /*val istrazivanje = getIstrazivanjeByNaziv()
                AppDatabase.getInstance(context).istrazivanjeDao().dodajIstrazivanje(istrazivanje)*/
                val ankete = AnketaRepository.getAnketeByGrupa(grupa.id)

                if (ankete != null)
                    for (anketa in ankete)
                        AppDatabase.getInstance(context).anketaDao().dodajAnketu(anketa)
            }
        }

        suspend fun getGrupeZaAnketu(anketaId: Int): List<Grupa>? {
            return withContext(Dispatchers.IO) {
                val response = ApiAdapter.retrofit.getGrupeZaAnketu(anketaId)
                return@withContext response.body()
            }
        }

        suspend fun getIstrazivanjaZaAnketu(anketaId: Int) : List<Istrazivanje> {
            return withContext(Dispatchers.IO){
                val grupeZaAnketu = getGrupeZaAnketu(anketaId) ?: return@withContext emptyList()

                val listaIstrazivanja = ArrayList<Istrazivanje>()

                for(grupaZaAnketu in grupeZaAnketu)
                    for(istrazivanje in getIstrazivanja())
                        if(grupaZaAnketu.id == istrazivanje.id)
                            listaIstrazivanja+=istrazivanje

                return@withContext listaIstrazivanja
            }
        }

        suspend fun getIstrazivanjaByGodina(godina: Int) : List<Istrazivanje> {
            return withContext(Dispatchers.IO){
                val listaIstrazivanja = ArrayList<Istrazivanje>()
                val istrazivanja = getIstrazivanja()

                for(istrazivanje in istrazivanja)
                    if(istrazivanje.godina == godina)
                        listaIstrazivanja+=istrazivanje

                return@withContext listaIstrazivanja
            }
        }

        suspend fun getIstrazivanjeByNaziv(nazivIstrazivanja: String) : Istrazivanje? {
            return withContext(Dispatchers.IO){
                val istrazivanja = getIstrazivanja()
                for (istrazivanje in istrazivanja)
                    if (istrazivanje.naziv == nazivIstrazivanja)
                        return@withContext istrazivanje
                return@withContext null
            }
        }

        suspend fun getGrupaZaIstrazivanje(idIstrazivanja : Int, nazivGrupe: String) : Grupa? {
            return withContext(Dispatchers.IO){
                val grupe = getGrupeZaIstrazivanje(idIstrazivanja)
                for(grupa in grupe)
                    if(grupa.naziv == nazivGrupe)
                        return@withContext grupa
                return@withContext null
            }
        }

        fun setContext(_context: Context) {
            context = _context
        }
    }
}

