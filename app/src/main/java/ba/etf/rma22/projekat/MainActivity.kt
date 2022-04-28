package ba.etf.rma22.projekat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.SystemClock.sleep
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.test.espresso.Espresso
import androidx.viewpager2.widget.ViewPager2
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.Pitanje
import ba.etf.rma22.projekat.data.models.pitanja
import ba.etf.rma22.projekat.data.repositories.AnketaRepository
import ba.etf.rma22.projekat.data.repositories.PitanjeAnketaRepository
import ba.etf.rma22.projekat.view.*
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {

    lateinit var viewPager: ViewPager2
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var viewPagerAdapter2: ViewPagerAdapter
    var listaFragmenata: ArrayList<Fragment> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager = findViewById(R.id.pager)
        val fragments = mutableListOf(
            FragmentAnkete(),
            FragmentIstrazivanje()
        )
        viewPager.offscreenPageLimit = 2
        viewPagerAdapter = ViewPagerAdapter(supportFragmentManager,fragments,lifecycle)
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                if(position == 0)
                    refreshSecondFragmentIstrazivanjeText()
            }
        })
        viewPager.adapter = viewPagerAdapter
    }

    fun refreshSecondFragmentPorukaText(poruka: String) {
        Handler(Looper.getMainLooper()).postDelayed({
            viewPagerAdapter.refreshFragment(1,FragmentPoruka(poruka))
        },500)
    }

    fun refreshSecondFragmentIstrazivanjeText() {
        Handler(Looper.getMainLooper()).postDelayed({
            viewPagerAdapter.refreshFragment(1, FragmentIstrazivanje())
        }, 500)
    }


    fun showAnketaDetails(anketa: Anketa) {
        val fragmentPitanje = FragmentPitanje()
        viewPagerAdapter.refreshFragment(0,fragmentPitanje)
        var listaPitanja = PitanjeAnketaRepository.getPitanja(anketa.naziv,anketa.nazivIstrazivanja).size
        var brojac: Int = 0
        val fragments = mutableListOf(
            fragmentPitanje
        )
        viewPagerAdapter2 = ViewPagerAdapter(supportFragmentManager,
            fragments.toMutableList(),lifecycle)
        val dugme: Button
        while(brojac < listaPitanja) {
            viewPagerAdapter2.add(brojac,fragmentPitanje)
            brojac++
        }
    }
}