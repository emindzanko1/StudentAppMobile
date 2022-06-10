package ba.etf.rma22.projekat

import ba.etf.rma22.projekat.data.models.Istrazivanje
import ba.etf.rma22.projekat.viewmodel.UpisIstrazivanjeViewModel
import org.junit.Assert.*
import org.junit.Test
import org.hamcrest.CoreMatchers.`is` as Is
import org.hamcrest.Matchers.*

class UpisIstrazivanjeTest {
    val upisIstrazivanjeViewModel = UpisIstrazivanjeViewModel()

    /*@Test
    fun getAll() {
        val svaIstrazivanja = upisIstrazivanjeViewModel.getAll()
        assertFalse(svaIstrazivanja.isEmpty())
        assertNotEquals(svaIstrazivanja.size,45)
        assertEquals(svaIstrazivanja.size, 15)
        assertThat(
            svaIstrazivanja, not(
                hasItem<Istrazivanje>(
                    hasProperty("naziv", Is("SPT"))
                )
            )
        )
        assertThat(
            svaIstrazivanja, (
                hasItem<Istrazivanje>(
                    hasProperty("naziv", Is("TPG"))
                )
            )
        )
    }

    @Test
    fun getUpisani() {
        val upisanoIstrazivanje = upisIstrazivanjeViewModel.getUpisani()
        assertFalse(upisanoIstrazivanje.isEmpty())
        assertNotEquals(upisanoIstrazivanje.size, 3)
        assertEquals(upisanoIstrazivanje.size,4)
        assertThat(
            upisanoIstrazivanje, hasItem<Istrazivanje>(
                hasProperty("naziv", Is("TDG"))
            )
        )
        assertThat(
            upisanoIstrazivanje, hasItem<Istrazivanje>(
                hasProperty("naziv", Is("PTG"))
            )
        )
        assertThat(
            upisanoIstrazivanje, not(
                hasItem<Istrazivanje>(
                    hasProperty("naziv", Is("DDG"))
                )
            )
        )
        assertThat(
            upisanoIstrazivanje, not(
                hasItem<Istrazivanje>(
                    hasProperty("naziv", Is("TPPG"))
                )
            )
        )
        assertThat(
            upisanoIstrazivanje, not(
                hasItem<Istrazivanje>(
                    hasProperty("naziv", Is("PPPG"))
                )
            )
        )
    }

   @Test
    fun getGroupsByIstrazivanje() {
        val grupeZaIstrazivanje = upisIstrazivanjeViewModel.getGroupsByIstrazivanje("PPG")
        assertFalse(grupeZaIstrazivanje.isEmpty())
        assertNotEquals(grupeZaIstrazivanje.size,1)
        assertEquals(grupeZaIstrazivanje.size, 3)
        assertFalse(grupeZaIstrazivanje.toString().contains("DPG-grupa1"))
    }

    @Test
    fun getIstrazivanjeByGodina() {
        val grupeZaIstrazivanje = upisIstrazivanjeViewModel.getIstrazivanjeByGodina(2)
        assertFalse(grupeZaIstrazivanje.isEmpty())
        assertNotEquals(grupeZaIstrazivanje.size,2)
        assertEquals(grupeZaIstrazivanje.size, 3)
        assertFalse(grupeZaIstrazivanje.toString().contains("PPPG"))
    }*/
}