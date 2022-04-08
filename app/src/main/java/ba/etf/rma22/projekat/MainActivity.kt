package ba.etf.rma22.projekat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma22.projekat.view.AnketaListAdapter
import ba.etf.rma22.projekat.viewmodel.AnketaListViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var ankete: RecyclerView
    private lateinit var anketeAdapter: AnketaListAdapter
    private var anketeViewModel = AnketaListViewModel()
    private lateinit var spinner: Spinner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ankete = findViewById(R.id.listaAnketa)
        spinner = findViewById(R.id.filterAnketa)
        ankete.layoutManager = GridLayoutManager(applicationContext,2,
            LinearLayoutManager.VERTICAL,false)
        anketeAdapter = AnketaListAdapter(listOf())
        ankete.adapter = anketeAdapter
        val opcije = listOf("Sve moje ankete","Sve ankete","Urađene ankete","Buduće ankete","Prošle ankete")
        spinner.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,opcije)
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
    }
}