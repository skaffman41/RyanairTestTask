package ru.alexnimas.ryanairtesttask.ui.flights

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

class FlightsFragment: Fragment() {
    private val viewModel by viewModels<FlightsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}