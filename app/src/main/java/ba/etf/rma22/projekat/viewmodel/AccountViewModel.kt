package ba.etf.rma22.projekat.viewmodel

import android.content.Context
import ba.etf.rma22.projekat.data.repositories.*

class AccountViewModel() {

    suspend fun postaviHash(acHash: String): Boolean =
        AccountRepository.postaviHash(acHash)

    suspend fun getHash(): String = AccountRepository.getHash()

    suspend fun postaviContext(context: Context) {
        AccountRepository.setContext(context)
        AnketaRepository.setContext(context)
        OdgovorRepository.setContext(context)
        PitanjeAnketaRepository.setContext(context)
        IstrazivanjeIGrupaRepository.setContext(context)
        TakeAnketaRepository.setContext(context)
    }
}