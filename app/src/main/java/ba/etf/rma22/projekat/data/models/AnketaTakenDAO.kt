package ba.etf.rma22.projekat.data.models

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AnketaTakenDAO {

    @Query("DELETE FROM AnketaTaken")
    suspend fun obrisiSve()

    @Insert
    suspend fun dodajAnketaTaken(anketaTaken: AnketaTaken)
}