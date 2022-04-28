package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.data.models.Pitanje
import ba.etf.rma22.projekat.data.models.pitanja
import ba.etf.rma22.projekat.data.models.pitanjaZaAnketu

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
}