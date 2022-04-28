package ba.etf.rma22.projekat.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import ba.etf.rma22.projekat.R

class FragmentPitanje() : Fragment() {

    private lateinit var textView: TextView
    private lateinit var listView: ListView
    private lateinit var button: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_pitanje,container,false)
        textView = view.findViewById(R.id.tekstPitanja)
        listView = view.findViewById(R.id.odgovoriLista)
        button = view.findViewById(R.id.dugmeZaustavi)
        return view
    }

    companion object {
        fun newInstance() : FragmentPitanje = FragmentPitanje()
    }
}