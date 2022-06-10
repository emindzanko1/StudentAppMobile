package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.models.Istrazivanje
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object IstrazivanjeIGrupaRepository {

    suspend fun getIstrazivanja(offset: Int) : List<Istrazivanje>? {
        return withContext(Dispatchers.IO){
            val response = ApiAdapter.retrofit.getIstrazivanja(offset)
            return@withContext response.body()
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
            return@withContext response.body()
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
}