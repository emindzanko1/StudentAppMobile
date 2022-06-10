package ba.etf.rma22.projekat.viewmodel

import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.repositories.AnketaRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class AnketaListViewModel {

    private val scope = CoroutineScope(Job() + Dispatchers.Main)

    fun getMyAnkete(onSuccess: (ankete: List<Anketa>) -> Unit, onError: () -> Unit) {
        scope.launch {
            when(val result = AnketaRepository.getMyAnkete()){
                else -> onSuccess.invoke(result)
            }
        }
    }

    fun getAll(onSuccess: (ankete: List<Anketa>) -> Unit, onError: () -> Unit)  {
        scope.launch {
            when(val result = AnketaRepository.getAll()){
                is List<Anketa> -> onSuccess.invoke(result)
                else -> onError.invoke()
            }
        }
    }

    fun getDone(onSuccess: (ankete: List<Anketa>) -> Unit, onError: () -> Unit) {
        scope.launch {
            when(val result = AnketaRepository.getDone()){
                else -> onSuccess.invoke(result)
            }
        }
    }

    fun getFuture(onSuccess: (ankete: List<Anketa>) -> Unit, onError: () -> Unit) {
        scope.launch {
            when(val result = AnketaRepository.getFuture()){
                else -> onSuccess.invoke(result)
            }
        }
    }

    fun getNotTaken(onSuccess: (ankete: List<Anketa>) -> Unit, onError: () -> Unit) {
        scope.launch {
            when(val result = AnketaRepository.getNotTaken()){
                else -> onSuccess.invoke(result)
            }
        }
    }
}