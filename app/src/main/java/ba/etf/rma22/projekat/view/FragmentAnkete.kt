package ba.etf.rma22.projekat.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma22.projekat.MainActivity
import ba.etf.rma22.projekat.R
import ba.etf.rma22.projekat.data.models.Anketa
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
        val view = inflater.inflate(R.layout.fragment_ankete,container,false)
        ankete = view.findViewById(R.id.listaAnketa)
        ankete.layoutManager = GridLayoutManager(activity,2)
        anketeAdapter = AnketaListAdapter(arrayListOf()) { anketa ->
            suspend { (activity as MainActivity).showAnketaDetails(anketa) }
        }
        ankete.adapter = anketeAdapter
        //anketeAdapter.updateAnkete(anketeViewModel.getMyAnkete())
        spinner = view.findViewById(R.id.filterAnketa)
        ankete.adapter = anketeAdapter
        val opcije = listOf("Sve moje ankete","Sve ankete","Urađene ankete","Buduće ankete","Prošle ankete")
        spinner.adapter = ArrayAdapter(view.context,android.R.layout.simple_list_item_1,opcije)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                when(p2){
                    0-> anketeViewModel.getMyAnkete(onSuccess = ::onSuccess, onError = ::onError)
                    1 -> anketeViewModel.getAll(onSuccess = ::onSuccess, onError = ::onError)
                    2 -> anketeViewModel.getDone(onSuccess = ::onSuccess, onError = ::onError)
                    3 -> anketeViewModel.getFuture(onSuccess = ::onSuccess, onError = ::onError)
                    else -> anketeViewModel.getNotTaken(onSuccess = ::onSuccess, onError = ::onError)
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

    fun onSuccess(ankete: List<Anketa>) {
        anketeAdapter.updateAnkete(ankete)
    }

    fun onError() {
        val toast = Toast.makeText(context, "Greska", Toast.LENGTH_SHORT)
        toast.show()
    }
}


