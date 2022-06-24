package ba.etf.rma22.projekat.data.models

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity data class Message(@SerializedName("message") val message: String)
