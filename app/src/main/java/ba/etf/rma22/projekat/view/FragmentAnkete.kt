package ba.etf.rma22.projekat.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma22.projekat.MainActivity
import ba.etf.rma22.projekat.R
import ba.etf.rma22.projekat.viewmodel.AnketaListViewModel

class FragmentAnkete : Fragment() {

    private lateinit var ankete: RecyclerView
    private lateinit var anketeAdapter: AnketaListAdapter
    private lateinit var spinner: Spinner
    private var anketeViewModel = AnketaListViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_ankete,container,false)
        ankete = view.findViewById(R.id.listaAnketa)
        ankete.layoutManager = GridLayoutManager(activity,2)
        anketeAdapter = AnketaListAdapter(arrayListOf()) { anketa ->
            (activity as MainActivity).showAnketaDetails(anketa)
        }
        ankete.adapter = anketeAdapter
        anketeAdapter.updateAnkete(anketeViewModel.getMyAnkete())
        spinner = view.findViewById(R.id.filterAnketa)
        ankete.adapter = anketeAdapter
        val opcije = listOf("Sve moje ankete","Sve ankete","Urađene ankete","Buduće ankete","Prošle ankete")
        spinner.adapter = ArrayAdapter(view.context,android.R.layout.simple_list_item_1,opcije)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                when(p2){
                    0-> anketeAdapter.updateAnkete(anketeViewModel.getMyAnkete())
                    1 -> anketeAdapter.updateAnkete(anketeViewModel.getAll())
                    2 -> anketeAdapter.updateAnkete(anketeViewModel.getDone())
                    3 -> anketeAdapter.updateAnkete(anketeViewModel.getFuture())
                    else -> anketeAdapter.updateAnkete(anketeViewModel.getNotTaken())
                }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
        return view
    }

    companion object {
        fun newInstance() : FragmentAnkete = FragmentAnkete()
    }
}


