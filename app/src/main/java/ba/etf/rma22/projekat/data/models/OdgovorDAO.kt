package ba.etf.rma22.projekat.data.models

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface OdgovorDAO {

    @Query("DELETE FROM Odgovor")
    suspend fun obrisiSve()

    @Insert
    suspend fun dodajOdgovor(odgovor: Odgovor)

    @Query("SELECT MAX(id)+1 FROM Odgovor")
    suspend fun getSljedeciId(): Long

}