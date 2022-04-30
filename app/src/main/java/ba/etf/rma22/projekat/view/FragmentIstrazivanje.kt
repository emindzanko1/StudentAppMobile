package ba.etf.rma22.projekat.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.Fragment
import ba.etf.rma22.projekat.MainActivity
import ba.etf.rma22.projekat.R
import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.models.Istrazivanje
import ba.etf.rma22.projekat.viewmodel.UpisIstrazivanjeViewModel

class FragmentIstrazivanje : Fragment() {

    private lateinit var spinnerZaGodine: Spinner
    private lateinit var spinnerZaIstrazivanje: Spinner
    private lateinit var spinnerZaGrupe: Spinner
    private lateinit var dodajIstrazivanjeDugme: Button
    private lateinit var spinnerZaGodineAdapter: ArrayAdapter<String>
    private lateinit var spinnerZaIstrazivanjeAdapter: ArrayAdapter<String>
    private lateinit var spinnerZaGrupeAdapter: ArrayAdapter<String>
    private var listaIstrazivanja = arrayListOf<String>()
    private var listaGrupa: ArrayList<String> = arrayListOf()
    private var upisIstrazivanjeViewModel = UpisIstrazivanjeViewModel()
    private var godina = ""
    private var istrazivanje = ""
    private var grupa = ""

    private var listener = object : AdapterView.OnItemSelectedListener{
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

    private fun izIstrazivanjaUString(istrazivanje: List<Istrazivanje>) : List<String>{
        var listaStringova: List<String> = listOf()
        for(ime in istrazivanje)
            listaStringova+=ime.naziv
        return listaStringova
    }

    private fun izGrupaUString(grupa: List<Grupa>) : List<String>{
        var listaStringova: List<String> = listOf()
        for(ime in grupa)
            listaStringova+=ime.naziv
        return listaStringova
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_istrazivanje,container,false)
        val godine = listOf("Odaberite godinu","1","2","3","4","5")

        spinnerZaGodine = view.findViewById(R.id.odabirGodina)
        spinnerZaGodineAdapter = ArrayAdapter(view.context,android.R.layout.simple_list_item_1,godine)
        spinnerZaGodine.adapter = spinnerZaGodineAdapter

        spinnerZaIstrazivanje = view.findViewById(R.id.odabirIstrazivanja)
        spinnerZaIstrazivanjeAdapter = ArrayAdapter(view.context,android.R.layout.simple_list_item_1,listaIstrazivanja)
        spinnerZaIstrazivanje.adapter = spinnerZaIstrazivanjeAdapter

        spinnerZaGrupe = view.findViewById(R.id.odabirGrupa)
        spinnerZaGrupeAdapter= ArrayAdapter(view.context,android.R.layout.simple_list_item_1,listaGrupa)
        spinnerZaGrupe.adapter = spinnerZaGrupeAdapter

        dodajIstrazivanjeDugme = view.findViewById(R.id.dodajIstrazivanjeDugme)
        dodajIstrazivanjeDugme.isEnabled = false

        spinnerZaGodine.onItemSelectedListener = listener
        spinnerZaIstrazivanje.onItemSelectedListener = listener
        spinnerZaGrupe.onItemSelectedListener = listener

        dodajIstrazivanjeDugme.setOnClickListener{
            upisIstrazivanjeViewModel.upisiIstrazivanje(godina,istrazivanje,grupa)
            val poruka = "Uspješno ste upisani u grupu " + grupa + " istraživanja " + istrazivanje + "!"
            (activity as MainActivity).refreshSecondFragmentPorukaText(poruka)
        }
        return view
    }

    companion object{
        fun newInstance() : FragmentIstrazivanje = FragmentIstrazivanje()
    }
}