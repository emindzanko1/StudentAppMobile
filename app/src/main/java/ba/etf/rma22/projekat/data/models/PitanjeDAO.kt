package ba.etf.rma22.projekat.data.models

import androidx.room.*

@Dao
interface PitanjeDAO {

    @Query("DELETE FROM Pitanje")
    suspend fun obrisiSve()

    @Insert
    suspend fun dodajPitanje(pitanje: Pitanje)

    @Transaction
    @Query("SELECT * FROM Pitanje")
    suspend fun getPitanja(): List<Pitanje>

}