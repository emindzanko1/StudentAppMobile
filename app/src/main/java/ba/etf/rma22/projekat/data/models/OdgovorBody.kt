package ba.etf.rma22.projekat.data.models

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class OdgovorBody(
    @SerializedName("odgovor") val odgovor: Int,
    @SerializedName("pitanje") val pitanje: Int,
    @SerializedName("progres") val progres: Int
)