package ba.etf.rma22.projekat.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import ba.etf.rma22.projekat.MainActivity
import ba.etf.rma22.projekat.R

class FragmentPredaj : Fragment() {

    private lateinit var textView: TextView
    private lateinit var button: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_predaj,container,false)
        textView = view.findViewById(R.id.progresTekst)!!
        button = view.findViewById(R.id.dugmePredaj)!!

        textView.text = "100%"

        button.setOnClickListener(){

            val poruka = "Završili ste anketu " + " u okviru istraživanja "

            (activity as MainActivity).viewPagerAdapter.pitanja(poruka)
            (activity as MainActivity).viewPager.currentItem = 1
            (activity as MainActivity).viewPager.adapter = (activity as MainActivity).viewPagerAdapter

        }

        return view
    }

    companion object {
        fun newInstance() : FragmentPredaj = FragmentPredaj()
    }
}