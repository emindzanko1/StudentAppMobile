package ba.etf.rma22.projekat.view

import android.annotation.SuppressLint
import android.content.Context
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma22.projekat.R
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.Istrazivanje
import ba.etf.rma22.projekat.viewmodel.UpisIstrazivanjeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class AnketaListAdapter(private var ankete: List<Anketa>, private val onItemClicked: (anketa : Anketa) -> Unit) : RecyclerView.Adapter<AnketaListAdapter.AnketaViewHolder>() {

    override fun getItemCount(): Int = ankete.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnketaViewHolder {
        val layoutInflater =
            LayoutInflater.from(parent.context).inflate(R.layout.anketa, parent, false)
        return AnketaViewHolder(layoutInflater)
    }

    private fun zaokruziProgres(progres: Float) : Int {
        val broj : Int = (progres*100).toInt()
        if(broj > 84)
            return 100
        else if(broj in 6..84) {
            for (i in 84 downTo 5 step 20)
                if (i - broj < 20)
                    return i-4
        }
        return 0
    }

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    override fun onBindViewHolder(holder: AnketaViewHolder, position: Int) {

        val upisIstrazivanjeViewModel = UpisIstrazivanjeViewModel()

        GlobalScope.launch(Dispatchers.IO){

            val listaIstrazivanja : ArrayList<Istrazivanje> = upisIstrazivanjeViewModel.getIstrazivanjaZaAnketu(ankete[position].id)
                .distinctBy { istrazivanje -> istrazivanje.id } as ArrayList<Istrazivanje>

            var naziviIstrazivanja = ""

            for (i in 0 until listaIstrazivanja.size) {
                naziviIstrazivanja += listaIstrazivanja[i].naziv
                if (i != listaIstrazivanja.size - 1)
                    naziviIstrazivanja += ", "
            }
        }
        holder.textView1.text = ankete[position].naziv
        holder.textView2.text = ankete[position].nazivIstrazivanja
        holder.progressBar.max = 100
        val progres = zaokruziProgres(ankete[position].progres!!)
        var boja = ""
        val danasnjiDatum: Date = Calendar.getInstance().time
        val formatiraj = SimpleDateFormat("dd.MM.yyyy")
        if(ankete[position].datumRada != null && ankete[position].datumPocetak.before(ankete[position].datumRada) && ankete[position].datumKraj!!.after(ankete[position].datumRada)
            && danasnjiDatum.after((ankete[position].datumRada))) {
            boja = "plava"
            val datum = ankete[position].datumRada
            holder.textView3.text = "Anketa urađena: " + formatiraj.format(datum)
            holder.progressBar.setProgress(100)
        }
       else if(ankete[position].datumRada != null && ankete[position].datumPocetak.before(ankete[position].datumRada) && ankete[position].datumKraj!!.after(ankete[position].datumRada)
            && danasnjiDatum.before(ankete[position].datumRada)) {
            boja = "zelena"
            val datum = ankete[position].datumKraj
            holder.textView3.text = "Vrijeme zatvaranja: " + formatiraj.format(datum)
            holder.progressBar.progress = progres
        }
        else if(ankete[position].datumPocetak.after(danasnjiDatum)){
            boja = "zuta"
            val datum = ankete[position].datumPocetak
            holder.textView3.text = "Vrijeme aktiviranja: " + formatiraj.format(datum)
            holder.progressBar.progress = 0
        }
        else{
            boja = "crvena"
            val datum = ankete[position].datumKraj
            if(datum != null)
            holder.textView3.text = "Anketa zatvorena: " + formatiraj.format(datum)
            holder.progressBar.progress = progres
        }
        val context: Context = holder.imageView.context
        val id: Int = context.resources.getIdentifier(boja, "drawable", context.packageName)
        holder.imageView.setImageResource(id)
        holder.itemView.setOnClickListener{
            onItemClicked(ankete[position])
        }
    }

    inner class AnketaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView1: TextView = itemView.findViewById(R.id.textView1)
        val textView2: TextView = itemView.findViewById(R.id.textView2)
        val imageView: ImageView = itemView.findViewById(R.id.crvena)
        val progressBar: ProgressBar = itemView.findViewById(R.id.progresZavrsetka)
        val textView3: TextView = itemView.findViewById(R.id.textView3)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateAnkete(ankete: List<Anketa>) {
        this.ankete = ankete.sortedBy { anketa -> anketa.datumPocetak }
        notifyDataSetChanged()
    }

}

