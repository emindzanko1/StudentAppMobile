package ba.etf.rma22.projekat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton

class UpisIstrazivanje : AppCompatActivity() {
    private lateinit var spinner: Spinner
    private lateinit var spinner2: Spinner
    private lateinit var spinner3: Spinner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upis_istrazivanje)
        val godine = listOf("1","2","3","4","5")
        spinner = findViewById(R.id.odabirGodina)
        spinner.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,godine)
        val istrazivanja = listOf("")
        /*spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                when(p2){
                }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }*/
    }
}