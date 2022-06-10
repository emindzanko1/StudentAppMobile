package ba.etf.rma22.projekat.data.models

import com.google.gson.annotations.SerializedName

class Grupa(
    @SerializedName("id") val id: Int,
    @SerializedName("naziv") val naziv: String,
)  {
    override fun equals(other: Any?) : Boolean{
        if(javaClass != other?.javaClass) return false
        if(this === other) return true
        other as Grupa
        return (id == other.id)
    }
}



