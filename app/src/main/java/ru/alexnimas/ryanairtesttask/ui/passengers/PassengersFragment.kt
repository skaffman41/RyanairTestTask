package ru.alexnimas.ryanairtesttask.ui.passengers

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.alexnimas.ryanairtesttask.R
import ru.alexnimas.ryanairtesttask.databinding.FragmentPassengersBinding

@AndroidEntryPoint
class PassengersFragment : Fragment(R.layout.fragment_passengers) {
    private val viewModel by viewModels<PassengersViewModel>()
    private val binding: FragmentPassengersBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
    }

    private fun setListeners() {
        binding.apply.setOnClickListener {
            val adult = binding.adult.text.toString().toIntOrNull() ?: 0
            val children = binding.children.text.toString().toIntOrNull() ?: 0
            val teen = binding.teen.text.toString().toIntOrNull() ?: 0
            val infant = binding.infant.text.toString().toIntOrNull() ?: 0
            viewModel.applyPassengers(adult, children, teen, infant)
            findNavController().popBackStack()
        }
    }
}
