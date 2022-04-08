package ba.etf.rma22.projekat.view

import android.animation.ObjectAnimator
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
import java.util.*
import kotlin.math.roundToInt

class AnketaListAdapter(private var ankete: List<Anketa>) : RecyclerView.Adapter<AnketaListAdapter.AnketaViewHolder>() {

    override fun getItemCount(): Int = ankete.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnketaViewHolder {
        val layoutInflater =
            LayoutInflater.from(parent.context).inflate(R.layout.anketa, parent, false)
        return AnketaViewHolder(layoutInflater)
    }

    private fun zaokruziProgres(progres: Float) : Int {
        val broj : Int = (progres*100).roundToInt()
        for(i in 100 downTo 0 step 20)
            if(i-broj < 10)
                return i
        return 0
    }

    override fun onBindViewHolder(holder: AnketaViewHolder, position: Int) {
        holder.textView1.text = ankete[position].naziv
        holder.textView2.text = ankete[position].nazivIstrazivanja
        holder.progressBar.max = 100
        val progres : Int = zaokruziProgres(ankete[position].progres)
        var boja = ""
        val danasnjiDatum: Date = Calendar.getInstance().time
        val formatiraj = SimpleDateFormat("dd.MM.yyyy")
        if(ankete[position].datumRada != null && ankete[position].datumPocetka.before(ankete[position].datumRada) && ankete[position].datumKraja.after(ankete[position].datumRada)
            && danasnjiDatum.after((ankete[position].datumRada))) {
            boja = "plava"
            val datum = ankete[position].datumRada
            holder.textView3.text = "Anketa urađena: " + formatiraj.format(datum)
            holder.progressBar.setProgress(100)
        }
       else if(ankete[position].datumRada != null && ankete[position].datumPocetka.before(ankete[position].datumRada) && ankete[position].datumKraja.after(ankete[position].datumRada)
            && danasnjiDatum.before(ankete[position].datumRada)) {
            boja = "zelena"
            val datum = ankete[position].datumKraja
            holder.textView3.text = "Vrijeme zatvaranja: " + formatiraj.format(datum)
            holder.progressBar.setProgress(progres)
        }
        else if(ankete[position].datumPocetka.after(danasnjiDatum)){
            boja = "zuta";
            val datum = ankete[position].datumPocetka
            holder.textView3.text = "Vrijeme aktiviranja: " + formatiraj.format(datum)
            holder.progressBar.setProgress(0)
        }
        else{
            boja = "crvena"
            val datum = ankete[position].datumKraja
            holder.textView3.text = "Anketa zatvorena: " + formatiraj.format(datum)
            holder.progressBar.setProgress(progres)
        }
        val context: Context = holder.imageView.context
        var id: Int = context.resources.getIdentifier(boja, "drawable", context.packageName)
        holder.imageView.setImageResource(id)
    }

    inner class AnketaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView1: TextView = itemView.findViewById(R.id.textView1)
        val textView2: TextView = itemView.findViewById(R.id.textView2)
        val imageView: ImageView = itemView.findViewById(R.id.crvena)
        val progressBar: ProgressBar = itemView.findViewById(R.id.progresZavrsetka)
        val textView3: TextView = itemView.findViewById(R.id.textView3)
    }

    fun updateAnkete(ankete: List<Anketa>) {
        this.ankete = ankete.sortedBy { anketa -> anketa.datumPocetka }
        notifyDataSetChanged()
    }
}

