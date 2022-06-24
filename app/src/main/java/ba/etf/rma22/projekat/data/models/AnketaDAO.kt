package ba.etf.rma22.projekat.data.models

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AnketaDAO {

    @Query("DELETE FROM Anketa")
    suspend fun obrisiSve()

    @Insert
    suspend fun dodajAnketu(anketa: Anketa)

}