package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.data.models.AnketaTaken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object TakeAnketaRepository {
    suspend fun zapocniAnketu(idAnkete : Int?) : AnketaTaken? {
        return withContext(Dispatchers.IO) {
            val response = ApiAdapter.retrofit.zapocniAnketu(AccountRepository.acHash, idAnkete!!)
            if(response.body() == null)
                return@withContext null
            return@withContext response.body()
        }
    }

    suspend fun getPoceteAnkete() :List<AnketaTaken>? {
        return withContext(Dispatchers.IO){
            val response = ApiAdapter.retrofit.getPoceteAnkete(AccountRepository.acHash)
            if (response.body()?.isEmpty() == true)
                return@withContext null
            return@withContext response.body()
        }
    }
}