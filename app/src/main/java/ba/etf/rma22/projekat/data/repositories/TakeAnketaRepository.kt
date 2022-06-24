package ba.etf.rma22.projekat.data.repositories

import android.annotation.SuppressLint
import android.content.Context
import ba.etf.rma22.projekat.data.models.AnketaTaken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TakeAnketaRepository {
    companion object {

        private lateinit var context: Context

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

        fun setContext(_context: Context) {
            context = _context
        }

    }
}
