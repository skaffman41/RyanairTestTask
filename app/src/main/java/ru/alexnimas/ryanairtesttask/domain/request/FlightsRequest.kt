package ru.alexnimas.ryanairtesttask.domain.request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FlightsRequest(
    val dateout: String,
    val origin: String,
    val destination: String,
    val adt: Int,
    val chd: Int,
    val teen: Int,
    val inf: Int
): Parcelable