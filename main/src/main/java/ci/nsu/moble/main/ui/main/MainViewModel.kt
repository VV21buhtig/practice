package ci.nsu.moble.main.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    // Приватная изменяемая переменная (только ViewModel может менять)
    private val _counter = MutableLiveData<Int>(0)

    // Публичная неизменяемая переменная (Fragment только читает)
    val counter: LiveData<Int> = _counter

    // Функция для увеличения счетчика
    fun incrementCounter() {
        val currentValue = _counter.value ?: 0
        _counter.value = currentValue + 1
    }
}
