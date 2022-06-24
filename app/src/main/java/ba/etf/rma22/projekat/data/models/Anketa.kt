package ba.etf.rma22.projekat.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity
data class Anketa(
    @PrimaryKey @SerializedName("id") val id: Int,
    @ColumnInfo(name = "naziv") @SerializedName("naziv") val naziv: String,
    @ColumnInfo(name = "datumPocetak") @SerializedName("datumPocetak") val datumPocetak: Date,
    @ColumnInfo(name = "datumKraj") @SerializedName("datumKraj") val datumKraj: Date,
    @ColumnInfo(name = "trajanje") @SerializedName("trajanje") val trajanje: Int,
    val nazivIstrazivanja: String,
    val datumRada: Date?,
    val nazivGrupe: String,
    val progres: Float,
)  {
    override fun equals(other: Any?) : Boolean {
        if (javaClass != other?.javaClass) return false
        if (this === other) return true
        other as Anketa
        return (id == other.id)
    }
}

