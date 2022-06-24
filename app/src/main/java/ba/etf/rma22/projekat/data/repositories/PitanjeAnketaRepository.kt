package ba.etf.rma22.projekat.data.repositories

import android.content.Context
import ba.etf.rma22.projekat.data.models.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PitanjeAnketaRepository {
    companion object {
        private lateinit var context: Context

        suspend fun getPitanja(idAnkete: Int?) : List<Pitanje>? {
            return withContext(Dispatchers.IO) {
                val response = ApiAdapter.retrofit.getPitanja(idAnkete!!)
                return@withContext response.body()
            }
        }

        fun setContext(_context: Context) {
            context = _context
        }

    }
}

