package ba.etf.rma22.projekat.view

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import ba.etf.rma22.projekat.MainActivity
import ba.etf.rma22.projekat.R
import ba.etf.rma22.projekat.data.models.Pitanje

class FragmentPitanje(pitanje: Pitanje) : Fragment() {

    private lateinit var textView: TextView
    private lateinit var listView: ListView
    private lateinit var button: Button
    private val pitanje = pitanje
    private lateinit var listaAdapter: ArrayAdapter<String>
    private var listaOdgovora: ArrayList<String> = arrayListOf()
    var brojac: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_pitanje,container,false)
        textView = view.findViewById(R.id.tekstPitanja)
        listView = view.findViewById(R.id.odgovoriLista)
        button = view.findViewById(R.id.dugmeZaustavi)
        textView.text = pitanje.tekst
        listaOdgovora.addAll(pitanje.opcije)
        listaAdapter = ArrayAdapter(view.context,android.R.layout.simple_list_item_1,listaOdgovora)
        listView.adapter = listaAdapter

        val bojaZaOdgovor: Int = Color.parseColor("#0000FF")

        listView.setOnItemClickListener(AdapterView.OnItemClickListener{ parent, view, position, id ->
            (view as TextView).setTextColor(bojaZaOdgovor)
            brojac++
        })

        button.setOnClickListener{
            (activity as MainActivity).viewPagerAdapter.change()
            (activity as MainActivity).viewPager.adapter = (activity as MainActivity).viewPagerAdapter
        }

        return view
    }

    companion object{
        fun newInstance(pitanje: Pitanje) : FragmentPitanje = FragmentPitanje(pitanje)
    }
}