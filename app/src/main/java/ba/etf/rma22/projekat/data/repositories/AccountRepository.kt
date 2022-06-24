package ba.etf.rma22.projekat.data.repositories

import android.content.Context
import ba.etf.rma22.projekat.data.models.Account
import ba.etf.rma22.projekat.data.models.AppDatabase
import java.util.*

class AccountRepository {
    companion object {
        private lateinit var context: Context
        var acHash: String = "1f435a83-0b34-4476-972a-8b95673b8fa1"

        suspend fun postaviHash(acHash: String): Boolean {
            val brojAccountova = AppDatabase.getInstance(context).accountDao().getBrojAccountova()
            if (acHash == this.acHash) return true
            this.acHash = acHash
            if (brojAccountova == 0) {
                AppDatabase.getInstance(context).anketaDao().obrisiSve()
                AppDatabase.getInstance(context).grupaDao().obrisiSve()
                AppDatabase.getInstance(context).istrazivanjeDao().obrisiSve()
                AppDatabase.getInstance(context).odgovorDao().obrisiSve()
                AppDatabase.getInstance(context).accountDao()
                    .dodajAccount(Account(acHash, Date().toString()))
            } else {
                AppDatabase.getInstance(context).accountDao().obrisiSve()
                AppDatabase.getInstance(context).anketaDao().obrisiSve()
                AppDatabase.getInstance(context).grupaDao().obrisiSve()
                AppDatabase.getInstance(context).istrazivanjeDao().obrisiSve()
                AppDatabase.getInstance(context).odgovorDao().obrisiSve()
                AppDatabase.getInstance(context).accountDao()
                    .dodajAccount(Account(acHash, Date().toString()))
            }
            return true
        }

        fun getHash(): String = acHash

        fun setContext(_context: Context) {
            context = _context
        }
    }
}