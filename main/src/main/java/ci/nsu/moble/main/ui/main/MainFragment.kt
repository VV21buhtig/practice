package ci.nsu.moble.main.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ci.nsu.moble.main.R

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    // Подключаем ViewModel
    private val viewModel: MainViewModel by viewModels()

    // Переменные для UI элементов
    private lateinit var textViewCounter: TextView
    private lateinit var buttonIncrement: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Загружаем разметку
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        // Находим элементы по ID
        textViewCounter = view.findViewById(R.id.textViewCounter)
        buttonIncrement = view.findViewById(R.id.buttonIncrement)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Настраиваем клик по кнопке
        buttonIncrement.setOnClickListener {
            viewModel.incrementCounter()
        }

        // 2. Подписываемся на изменения данных во ViewModel
        // Как только counter изменится, выполнится код внутри {}
        viewModel.counter.observe(viewLifecycleOwner) { count ->
            textViewCounter.text = count.toString()
        }
    }
}
