package com.home

import com.entities.Barber
import com.repositories.BarberRepository

class LocationRepositoryImpl: BarberRepository {

    override fun getBarbers(): List<Barber> {
        return listOf()
    }
}