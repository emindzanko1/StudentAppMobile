package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.data.models.*

object PitanjeAnketaRepository {

     fun getPitanja(nazivAnkete: String, nazivIstrazivanja: String): List<Pitanje> {
        val listaPitanja : ArrayList<Pitanje> = arrayListOf()
         for(pitanjeAnketa in pitanjaZaAnketu()){
             if(pitanjeAnketa.anketa == nazivAnkete && pitanjeAnketa.istrazivanje == nazivIstrazivanja){
                 for(pitanje in pitanja())
                     if(pitanje.naziv == pitanjeAnketa.naziv)
                         listaPitanja.add(pitanje)
             }
         }
        return listaPitanja
    }

    fun getAll(): List<PitanjeAnketa> = pitanjaZaAnketu()

}