package ba.etf.rma22.projekat.data.models

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface GrupaDAO {

    @Query("DELETE FROM Grupa")
    suspend fun obrisiSve()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun dodajGrupu(grupa: Grupa)
}