package ba.etf.rma22.projekat

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import ba.etf.rma22.projekat.UtilTestClass.Companion.hasItemCount
import ba.etf.rma22.projekat.UtilTestClass.Companion.itemTest
import ba.etf.rma22.projekat.data.repositories.AnketaRepository
import ba.etf.rma22.projekat.data.repositories.IstrazivanjeRepository
import org.hamcrest.CoreMatchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MySpirala2AndroidTest {

    @get:Rule
    val intentsTestRule = IntentsTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun upisTest() {

        Espresso.onView(ViewMatchers.withId(R.id.filterAnketa)).perform(ViewActions.click())
        Espresso.onData(
            CoreMatchers.allOf(
                CoreMatchers.`is`(CoreMatchers.instanceOf(String::class.java)),
                CoreMatchers.`is`("Sve moje ankete")
            )
        ).perform(ViewActions.click())

        val anketePrije = AnketaRepository.getMyAnkete()
        val anketePrijeSize = anketePrije.size

        Espresso.onView(ViewMatchers.withId(R.id.listaAnketa)).check(hasItemCount(anketePrije.size))
        for (anketa in anketePrije) {
            itemTest(R.id.listaAnketa, anketa)
        }

        val nedodjeljeneAnkete = AnketaRepository.getAll().minus(AnketaRepository.getMyAnkete())
        val nedodjeljenaIstrazivanja = IstrazivanjeRepository.getAll().minus(IstrazivanjeRepository.getUpisani())

        var grupaVrijednost = ""
        var predmetNaziv = ""
        var godinaVrijednost = -1
        for (nk in nedodjeljeneAnkete) {
            for (np in nedodjeljenaIstrazivanja) {
                if (nk.nazivIstrazivanja == np.naziv) {
                    grupaVrijednost = nk.nazivGrupe
                    godinaVrijednost = np.godina
                    predmetNaziv = np.naziv
                }
            }
        }

        ViewMatchers.assertThat(
            "Nema neupisanih istraživanja sa anketama",
            godinaVrijednost,
            CoreMatchers.not(CoreMatchers.`is`(-1))
        )

        Espresso.onView(ViewMatchers.withSubstring("Uspješno ste upisani u grupu"))
        val anketePoslije = AnketaRepository.getMyAnkete()

        Espresso.onView(ViewMatchers.withId(R.id.listaAnketa)).check(hasItemCount(anketePoslije.size))
        for (anketa in anketePoslije) {
            itemTest(R.id.listaAnketa, anketa)
        }

        ViewMatchers.assertThat(
            "Nije dodan kviz nakon upisanog predmeta ",
            anketePoslije,
            CoreMatchers.not(anketePoslije.size)
        )
    }
}