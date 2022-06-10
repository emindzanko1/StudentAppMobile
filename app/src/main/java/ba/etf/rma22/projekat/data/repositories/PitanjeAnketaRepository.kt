package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.data.models.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object PitanjeAnketaRepository {
    suspend fun getPitanja(idAnkete: Int?) : List<Pitanje>? {
        return withContext(Dispatchers.IO) {
            val response = ApiAdapter.retrofit.getPitanja(idAnkete!!)
            return@withContext response.body()
        }
    }
}