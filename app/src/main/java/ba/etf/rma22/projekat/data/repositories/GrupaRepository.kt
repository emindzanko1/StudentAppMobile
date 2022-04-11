package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.models.grupe

object GrupaRepository {
    fun getGroupsByIstrazivanje(nazivIstrazivanja:String) : List<Grupa>{
       return grupe().filter { grupa ->  grupa.nazivIstrazivanja == nazivIstrazivanja}
    }
}