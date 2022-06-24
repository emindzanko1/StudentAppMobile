package ba.etf.rma22.projekat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import ba.etf.rma22.projekat.data.models.Account
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.AppDatabase
import ba.etf.rma22.projekat.data.repositories.AccountRepository
import ba.etf.rma22.projekat.data.repositories.PitanjeAnketaRepository
import ba.etf.rma22.projekat.view.*
import ba.etf.rma22.projekat.viewmodel.AccountViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var viewPager: ViewPager2
    lateinit var viewPagerAdapter: ViewPagerAdapter
    lateinit var viewPagerAdapter2: ViewPagerAdapter
    private lateinit var fragments: MutableList<Fragment>
    private var accountViewModel = AccountViewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch(Dispatchers.Main) {
            accountViewModel.postaviContext(applicationContext)
            AppDatabase.getInstance(applicationContext)
            AppDatabase.getInstance(applicationContext).accountDao().obrisiSve()
            AppDatabase.getInstance(applicationContext).accountDao()
                .dodajAccount(Account(AccountRepository.acHash,Date().toString()))
        }

        val payload = intent?.getStringExtra("payload")
        AccountRepository.setContext(applicationContext)
        if (payload != null) {
            GlobalScope.launch(Dispatchers.Main) {
                accountViewModel.postaviHash(payload)
                accountViewModel.postaviContext(applicationContext)
            }
        }

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


    suspend fun showAnketaDetails(anketa: Anketa) {
        val brojPitanja =
            PitanjeAnketaRepository.getPitanja(anketa.id)!!.size
        val listaPitanja =
            PitanjeAnketaRepository.getPitanja(anketa.id)
        fragments.removeAt(0)
        var brojac = 0
        while (brojac < brojPitanja) {
            val fragmentPitanje = FragmentPitanje(listaPitanja!![brojac])
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