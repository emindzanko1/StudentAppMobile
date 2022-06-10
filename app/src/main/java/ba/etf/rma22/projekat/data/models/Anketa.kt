package ba.etf.rma22.projekat.data.models

import com.google.gson.annotations.SerializedName
import java.util.*

class Anketa(
    @SerializedName("id") val id: Int,
    @SerializedName("naziv") val naziv: String,
    @SerializedName("datumPocetak") val datumPocetak: Date,
    @SerializedName("datumKraj") val datumKraj: Date,
    @SerializedName("trajanje") val trajanje: Int,
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
