package com.repositories

import com.entities.Barber

interface BarberRepository {
    fun getBarbers(): List<Barber>
}