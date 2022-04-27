package ba.etf.rma22.projekat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.models.Istrazivanje
import ba.etf.rma22.projekat.viewmodel.UpisIstrazivanjeViewModel

/*class UpisIstrazivanje : AppCompatActivity() {



    private var listener = object :AdapterView.OnItemSelectedListener{
        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            if(p0!!.id  == R.id.odabirGodina){
                godina = spinnerZaGodine.selectedItem.toString()
                if(godina != "Odaberite godinu") {
                    listaIstrazivanja.clear()
                    val lista = upisIstrazivanjeViewModel.getIstrazivanjeByGodina(godina.toInt())
                    val novaLista = izIstrazivanjaUString(lista)
                    listaIstrazivanja.add("Odaberite istrazivanje")
                    listaIstrazivanja.addAll(novaLista)
                    spinnerZaIstrazivanjeAdapter.notifyDataSetChanged()
                }
            }
            else if(p0.id == R.id.odabirIstrazivanja){
                istrazivanje = spinnerZaIstrazivanje.selectedItem.toString()
                if(istrazivanje != "Odaberite istrazivanje") {
                    listaGrupa.clear()
                    val lista = upisIstrazivanjeViewModel.getGroupsByIstrazivanje(istrazivanje)
                    val novaLista = izGrupaUString(lista)
                    listaGrupa.add("Odaberite grupu")
                    listaGrupa.addAll(novaLista)
                    spinnerZaGrupeAdapter.notifyDataSetChanged()
                }
            }
            else if(p0.id == R.id.odabirGrupa){
                grupa = spinnerZaGrupe.selectedItem.toString()
                if(grupa != "Odaberite grupu")
                dodajIstrazivanjeDugme.isEnabled = true
            }
        }

        override fun onNothingSelected(p0: AdapterView<*>?) {
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upis_istrazivanje)

}*/