package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.Istrazivanje
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

object AnketaRepository {

    suspend fun getAnketeByGrupa(idGrupe: Int): List<Anketa>? {
        return withContext(Dispatchers.IO) {
            val response = ApiAdapter.retrofit.getAnketeByGrupa(idGrupe)
            return@withContext response.body()
        }
    }

    suspend fun getMyAnkete() : List<Anketa> {
        return withContext(Dispatchers.IO){
            var listaAnketa = arrayListOf<Anketa>()
            for(grupa in IstrazivanjeIGrupaRepository.getUpisaneGrupe()!!)
                for(anketa in getAnketeByGrupa(grupa.id)!!)
                    listaAnketa.add(anketa)
            return@withContext listaAnketa
        }
    }

    suspend fun getAll(offset: Int) : List<Anketa>? {
        return withContext(Dispatchers.IO){
            val response = ApiAdapter.retrofit.getOffsetAnkete(offset)
            return@withContext response.body()
        }
    }

    suspend fun getAll() : List<Anketa>? {
        return withContext(Dispatchers.IO) {
            val listaAnketa = ArrayList<Anketa>()
            var brojac = 0
            while (true) {
                val lista = getAll(brojac)
                if (lista != null)
                    listaAnketa.addAll(lista)
                brojac++
                if (brojac == 5)
                    break
            }
            return@withContext listaAnketa
        }
    }

    suspend fun getById(id: Int) : Anketa? {
        return withContext(Dispatchers.IO){
            val response = ApiAdapter.retrofit.getAnketaById(id)
            return@withContext response.body()
        }
    }

    suspend fun getUpisane() : List<Anketa>? {
        return withContext(Dispatchers.IO){
            return@withContext getMyAnkete()
        }
    }

    suspend fun getDone() : List<Anketa> {
        return withContext(Dispatchers.IO) {
            var listaAnekta = ArrayList<Anketa>()
            var danasnjiDatum: Date = Date()
            for (anketa in getMyAnkete())
                if (anketa.datumRada != null && anketa.datumPocetak.before(anketa.datumRada) && anketa.datumKraj.after(
                        anketa.datumRada) && danasnjiDatum > anketa.datumRada)
                    listaAnekta += anketa
            return@withContext listaAnekta
        }
    }

    suspend fun getFuture() : List<Anketa> {
        return withContext(Dispatchers.IO) {
            var listaAnekta: List<Anketa> = listOf()
            val danasnjiDatum: Date = Date()
            for (anketa in getMyAnkete())
                if (anketa.datumPocetak.after(danasnjiDatum) || (anketa.datumRada != null && anketa.datumPocetak.before(
                        anketa.datumRada) && anketa.datumKraj.after(anketa.datumRada) && danasnjiDatum < anketa.datumRada))
                    listaAnekta += anketa
            return@withContext listaAnekta
        }
    }

    suspend fun getNotTaken() : List<Anketa> {
        return withContext(Dispatchers.IO) {
            var listaAnekta: List<Anketa> = listOf()
            val danasnjiDatum = Date()
            for (anketa in getMyAnkete())
                if(danasnjiDatum.after(anketa.datumPocetak) && anketa.datumRada == null) // !?
                //if (anketa.datumRada == null && danasnjiDatum.after(anketa.datumKraja))  bilo
                    listaAnekta += anketa
            return@withContext listaAnekta
        }
    }
}