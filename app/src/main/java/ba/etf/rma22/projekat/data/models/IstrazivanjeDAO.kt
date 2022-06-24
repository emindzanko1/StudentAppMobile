package ba.etf.rma22.projekat.data.models

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface IstrazivanjeDAO {

    @Query("DELETE FROM Istrazivanje")
    suspend fun obrisiSve()

    @Insert
    suspend fun dodajIstrazivanje(istrazivanje: Istrazivanje)

}