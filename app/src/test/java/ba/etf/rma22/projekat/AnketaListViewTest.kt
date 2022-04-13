package ba.etf.rma22.projekat

import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.repositories.AnketaRepository
import ba.etf.rma22.projekat.viewmodel.AnketaListViewModel
import junit.framework.Assert.assertEquals
import org.junit.Assert.*
import org.junit.Test
import org.hamcrest.CoreMatchers.`is` as Is
import org.hamcrest.Matchers.*

class AnketaListViewTest {

    @Test
    fun anketa(){
        val ankete = AnketaRepository.getAll()
        assertFalse(ankete.isEmpty())
        assertEquals(ankete.size,12)
        assertThat(ankete, hasItem<Anketa>(hasProperty("nazivIstrazivanja", Is("PPG"))))
        assertThat(ankete, not(hasItem<Anketa>(hasProperty("nazivIstrazivanja", Is("DPG")))))
        assertThat(ankete, hasItem<Anketa>(hasProperty("nazivIstrazivanja", Is("DDG"))))
        assertThat(ankete, not(hasItem<Anketa>(hasProperty("nazivIstrazivanja", Is("DPPG")))))
    }

    private val anketaListViewModel = AnketaListViewModel()

    @Test
    fun getAll() {
        val ankete = AnketaRepository.getAll()
        assertEquals(ankete.size, 12)
        assertFalse(ankete.isEmpty())
        assertThat(ankete, hasItem<Anketa>(
            hasProperty("nazivIstrazivanja", Is("PTG"))
        ))
        assertThat(ankete, not(hasItem<Anketa>(
            hasProperty("nazivIstrazivanja", Is("DPPG"))
        )))
        assertThat(ankete, not(hasItem<Anketa>(
            hasProperty("nazivIstrazivanja", Is("DTG"))
        )))
        assertThat(ankete, hasItem<Anketa>(
            hasProperty("nazivIstrazivanja", Is("TDG"))
        ))

    }

    @Test
    fun getMojiKvizovi() {
        val ankete = anketaListViewModel.getMyAnkete()
        assertTrue(ankete.isNotEmpty())
        assertEquals(ankete.size, 4)
        assertThat(ankete, hasItem<Anketa>(
            hasProperty("nazivIstrazivanja", Is("TDG"))
        ))
        assertThat(ankete, hasItem<Anketa>(
            hasProperty("nazivIstrazivanja", Is("PPG"))
        ))
        assertThat(ankete, not(hasItem<Anketa>(
            hasProperty("nazivIstrazivanja", Is("TPG")))
        ))
        assertThat(ankete, not(hasItem<Anketa>(
            hasProperty("nazivIstrazivanja", Is("PČG")))
        ))
    }

    @Test
    fun getFuture() {
        val ankete = anketaListViewModel.getFuture()
        assertEquals(ankete.size,2)
        assertFalse(ankete.isEmpty())
        assertThat(ankete, not(hasItem<Anketa>(
            hasProperty("naziv", Is("Anketa 9"))
        )))
        assertThat(ankete, hasItem<Anketa>(
            hasProperty("naziv", Is("Anketa 8"))
        ))
        assertThat(ankete, not(hasItem<Anketa>(
            hasProperty("progres",Is(0.41F)))))
        assertThat(ankete, hasItem<Anketa>(
            hasProperty("progres",Is(0.26F))))
    }

    @Test
    fun getDone() {
        val ankete = anketaListViewModel.getDone()
        assertTrue(ankete.isNotEmpty())
        assertNotEquals(ankete.size,4)
        assertEquals(ankete.size,1)
        assertThat(ankete, not(hasItem<Anketa>(
            hasProperty("progres",Is(0.41F)))))
        assertThat(ankete, hasItem<Anketa>(
            hasProperty("progres",Is(1.0F))))
    }

    @Test
    fun getNotTaken() {
        val ankete = anketaListViewModel.getNotTaken()
        assertTrue(ankete.isNotEmpty())
        assertEquals(ankete.size, 1)
        assertThat(ankete, not(hasItem<Anketa>(
            hasProperty("nazivGrupe", Is("PPG-grupa1"))
        )))
        assertThat(ankete, hasItem<Anketa>(
            hasProperty("nazivGrupe", Is("PPG-grupa2"))
        ))
        assertThat(ankete, hasItem<Anketa>(
            hasProperty("progres",Is(0.11F))))
    }
}