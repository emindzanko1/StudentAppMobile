package ba.etf.rma22.projekat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.repositories.PitanjeAnketaRepository
import ba.etf.rma22.projekat.view.*

class MainActivity : AppCompatActivity() {

    lateinit var viewPager: ViewPager2
    lateinit var viewPagerAdapter: ViewPagerAdapter
    lateinit var viewPagerAdapter2: ViewPagerAdapter
    private lateinit var fragments: MutableList<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager = findViewById(R.id.pager)
        fragments = mutableListOf(
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
        var brojPitanja =
            PitanjeAnketaRepository.getPitanja(anketa.naziv, anketa.nazivIstrazivanja).size
        var listaPitanja =
            PitanjeAnketaRepository.getPitanja(anketa.naziv, anketa.nazivIstrazivanja)
        fragments.removeAt(0)
        var brojac: Int = 0
        while (brojac < brojPitanja) {
            val fragmentPitanje = FragmentPitanje(listaPitanja[brojac])
            fragments.add(brojac, fragmentPitanje)
            brojac++
        }
        viewPagerAdapter2 = ViewPagerAdapter(
            supportFragmentManager,
            fragments, lifecycle
        )
        viewPager.adapter = viewPagerAdapter2
        val fragmentPredaj = FragmentPredaj()
        fragments.add(brojPitanja, fragmentPredaj)
    }
}