package ba.etf.rma22.projekat.data.models

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [Account::class, Grupa::class, Anketa::class, AnketaTaken::class, Odgovor::class, Pitanje::class, Istrazivanje::class], version = 4
)

@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun accountDao(): AccountDAO
    abstract fun anketaDao(): AnketaDAO
    abstract fun anketaTakenDao(): AnketaTakenDAO
    abstract fun grupaDao(): GrupaDAO
    abstract fun odgovorDao(): OdgovorDAO
    abstract fun pitanjeDao(): PitanjeDAO
    abstract fun istrazivanjeDao(): IstrazivanjeDAO

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = buildRoomDB(context)
                }
            }
            return INSTANCE!!
        }

        private fun buildRoomDB(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "RMA22DB"
            ).fallbackToDestructiveMigration().build()
    }
}
